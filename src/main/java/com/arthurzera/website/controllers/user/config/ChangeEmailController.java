package com.arthurzera.website.controllers.user.config;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.EmailPasswordForm;
import com.arthurzera.website.models.User;
import com.arthurzera.website.models.VerificationToken;

@Controller
public class ChangeEmailController extends BasicController {

	@RequestMapping("/users/{username}/config/change-email")
	public ModelAndView changeEmail(@PathVariable("username") String username) {
		ModelAndView mvc = super.mvc();
		User user = (User) mvc.getModel().get("currentUser");
		mvc.addObject("emailPasswordForm", new EmailPasswordForm());
		mvc.addObject("user", user);
		mvc.setViewName("users/change-email");
		return mvc;
	}

	@RequestMapping(value = "/users/{username}/config/change-email", method = RequestMethod.POST)
	public ModelAndView changeUserEmail(@PathVariable("username") String username, EmailPasswordForm emailPasswordForm,
			BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mvc = super.mvc();
		User user = (User) mvc.getModel().get("currentUser");
		if (!user.getUsername().equals(username)) {
			notifyService.addDangerMessage("You can't make configuration on a diferent account");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
		if (!passwordEncoder.matches(emailPasswordForm.getPassword(), user.getPasswordHash())) {
			notifyService.addDangerMessage("Wrong Password");
			mvc.setViewName("/users/change-email");
			return mvc;
		}
		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Fill the form correctly");
			mvc.setViewName("users/change-email");
			return mvc;
		}
		if (!emailPasswordForm.getPassword().equals(emailPasswordForm.getConfirmPassword())) {
			notifyService.addDangerMessage("Passwords must match!");
			mvc.setViewName("/users/change-email");
			return mvc;
		}

		try {
			String token = UUID.randomUUID().toString();
			userService.createVerificationToken(user, token);
			String confirmationUrl = request.getContextPath() + "/users/" + user.getUsername()
					+ "/config/changeEmailConfirmation?token=" + token + "&email=" + emailPasswordForm.getEmail();
			String message = "To confirm your email change click in the link below";
			emailService.sendSimpleMessage(emailPasswordForm.getEmail(), "Change Email Confirmation",
					message + "\n http://localhost:8181" + confirmationUrl);
			userService.edit(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		notifyService.addInfoMessage("A email has been sent to your new email address");
		mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
		return mvc;
	}

	@RequestMapping("/users/{username}/config/changeEmailConfirmation")
	public ModelAndView confirmEmailChange(@PathVariable("username") String username,
			@RequestParam("token") String token, @RequestParam("email") String email) {
		ModelAndView mvc = super.mvc();
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			notifyService.addDangerMessage("Invalid token");
			mvc.setViewName("redirect:/users/" + username + "/config");
			return mvc;

		}
		User user = verificationToken.getUser();
		if (!user.getUsername().equals(username)) {
			notifyService.addDangerMessage("You can't make configuration on a diferent account");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			notifyService.addDangerMessage("The token has expired");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
		user.getEmail().setEmail(email);
		userService.edit(user);
		notifyService.addSuccessMessage("Email change with success");
		mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
		return mvc;
	}
}
