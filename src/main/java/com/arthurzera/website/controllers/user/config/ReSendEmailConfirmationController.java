package com.arthurzera.website.controllers.user.config;

import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.EmailForm;
import com.arthurzera.website.models.User;

@Controller
public class ReSendEmailConfirmationController extends BasicController {

	@RequestMapping("/re-send-confirmation-email")
	public ModelAndView reSentEmailConfirmation() {
		ModelAndView mvc = super.mvc();
		User user = (User) mvc.getModel().get("currentUser");
		mvc.addObject("emailForm", new EmailForm());
		mvc.addObject("user", user);
		mvc.setViewName("users/re-send-confirmation-email");
		return mvc;
	}

	@RequestMapping(value = "/re-send-confirmation-email", method = RequestMethod.POST)
	public ModelAndView reSentEmailConfirmation(EmailForm emailForm, BindingResult bindingResult,
			HttpServletRequest request) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByEmail(emailForm.getEmail());
		if (user.isEnabled() || user.getEmail().isConfirmed()) {
			notifyService.addInfoMessage("Email already confirmed!");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
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
		notifyService.addInfoMessage("Confirmation email sent");
		mvc.setViewName("redirect:/users/login");
		return mvc;
	}

	@RequestMapping("/users/{username}/config/re-send-confirmation-email")
	public ModelAndView reSentEmailConfirmationUser(@PathVariable("username") String username,
			HttpServletRequest request) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByUsername(username);
		if (user.isEnabled() || user.getEmail().isConfirmed()) {
			notifyService.addInfoMessage("Email already confirmed!");
			mvc.setViewName("redirect:/users/" + username + "/config");
			return mvc;
		}
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
		notifyService.addInfoMessage("Confirmation email sent");
		mvc.setViewName("redirect:/users/" + username + "/config");
		return mvc;
	}

}
