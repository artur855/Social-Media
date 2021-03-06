package com.arthurzera.social.media.controllers.user;

import com.arthurzera.social.media.controllers.BasicController;
import com.arthurzera.social.media.forms.RegisterForm;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.VerificationToken;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController extends BasicController {

	@RequestMapping("/users/register")
	public ModelAndView register(RegisterForm registerForm) {
		ModelAndView mvc = super.mvc();
		if (mvc.getModel().get("currentUser") != null) {
			mvc.setViewName("redirect:/");
			return mvc;
		}
		mvc.setViewName("users/register");
		return mvc;
	}

	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public ModelAndView registerPage(@Valid RegisterForm registerForm, BindingResult bindingResult,
			HttpServletRequest request) {
		ModelAndView mvc = super.mvc();

		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Please fill the form correctly!");
			mvc.setViewName("users/register");
			return mvc;
		}
		if (userService.existsByUsername(registerForm.getUsername())) {
			notifyService.addDangerMessage("Username already used!");
			mvc.setViewName("users/register");
			return mvc;
		}
		if (!registerForm.getFullName().equals("")) {
			if (userService.existsByFullName(registerForm.getFullName())) {
				notifyService.addDangerMessage("Username already used!");
				mvc.setViewName("users/register");
				return mvc;
			}
		}
		if (userService.existsByEmail(registerForm.getEmail())) {
			notifyService.addDangerMessage("Email already used!");
			mvc.setViewName("users/register");
			return mvc;
		}
		if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
			notifyService.addDangerMessage("Passwords don't macth!");
			mvc.setViewName("users/register");
			return mvc;
		}
		User user = new User(registerForm.getUsername(), registerForm.getFullName(), registerForm.getEmail(),
				passwordEncoder.encode(registerForm.getPassword()));
		user.setBirthday(registerForm.getBirthday());
		user.addRole(roleService.findByRole("ROLE_USER"));
		userService.create(user);
		try {
			String token = UUID.randomUUID().toString();
			userService.createVerificationToken(user, token);
			String appUrl = request.getContextPath();
			String confirmationUrl = appUrl + "/users/registrationConfirm?token=" + token;
			String message = "To confirm your account click in the link below";
			emailService.sendSimpleMessage(user.getEmail().getEmail(), "Registration Confirmation",
					message + "\n http://localhost:8181" + confirmationUrl);
			user.getEmail().setEmailConfirmationSentOn(new Date());
			userService.edit(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		mvc.setViewName("redirect:/users/login");
		return mvc;
	}

	@RequestMapping(value = "/users/registrationConfirm")
	public ModelAndView confirmRegistration(@RequestParam("token") String token) {
		ModelAndView mvc = super.mvc();
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			notifyService.addDangerMessage("Invalid Token");
			mvc.setViewName("redirect:/error/badUser");
			return mvc;
		}
		User user = verificationToken.getUser();
		if (user.isEnabled()) {
			notifyService.addInfoMessage("User already confirmed!");
			mvc.setViewName("redirect:/users/login");
		}
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			notifyService.addDangerMessage("The token has expired");
			mvc.setViewName("redirect:/error/badUser");
			return mvc;
		}
		user.setEnabled(true);
		user.getEmail().setEmailConfirmedOn(new Date());
		user.getEmail().setEmailConfirmed(true);
		userService.edit(user);
		notifyService.addInfoMessage("Account activated");
		mvc.setViewName("redirect:/users/login");
		return mvc;
	}
}
