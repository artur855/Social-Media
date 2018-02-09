package com.arthurzera.social.media.controllers.user.config;

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
import com.arthurzera.social.media.controllers.BasicController;
import com.arthurzera.social.media.forms.ChangePasswordEmailForm;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.VerificationToken;

@Controller
public class ChangePasswordEmailController extends BasicController {
	
	
	@RequestMapping("/users/{username}/config/change-password-email")
	public ModelAndView changePasswordEmail(@PathVariable("username") String username, HttpServletRequest request) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByUsername(username);
		try {
			String token = UUID.randomUUID().toString();
			userService.createVerificationToken(user, token);
			String confirmationUrl = request.getContextPath() + "/users/" + user.getUsername()
					+ "/config/changePasswordEmail?token=" + token;
			String message = "To change your password click in the link below";
			emailService.sendSimpleMessage(user.getEmail().getEmail(), "Change Password",
					message + "\n http://localhost:8181" + confirmationUrl);
			userService.edit(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		notifyService.addInfoMessage("An email has been sent to your email address");
		mvc.setViewName("redirect:/users/"+username+"/config");
		return mvc;
	}
	@RequestMapping("/users/{username}/config/changePasswordEmail")
	public ModelAndView changePasswordEmail(@PathVariable("username")String username, @RequestParam("token") String token) {
		ModelAndView mvc = super.mvc();
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			notifyService.addDangerMessage("Invalid Token");
			mvc.setViewName("redirect:/users/"+username+"/config");
			return mvc;
		}
		User user = verificationToken.getUser();
		if(!user.getUsername().equals(username)) {
			notifyService.addDangerMessage("You can't make modifications on a diferent account");
			mvc.setViewName("redirect:/users/"+username+"/config");
			return mvc;
		}
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			notifyService.addDangerMessage("The token has expired");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
		
		mvc.addObject("changePasswordEmailForm", new ChangePasswordEmailForm());
		mvc.addObject("user", user);
		mvc.setViewName("users/change-password-email");
		return mvc;
	}
	
	@RequestMapping(value="/users/{username}/config/changePasswordEmail", method=RequestMethod.POST)
	public ModelAndView changePasswordEmail(@PathVariable("username") String username, ChangePasswordEmailForm changePasswordEmailForm, BindingResult bindingResult) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByUsername(username);
		if(!user.getUsername().equals(username)) {
			notifyService.addDangerMessage("You can't make configuration on a diferent account");
			mvc.setViewName("redirect:/users/"+username+"/config/changePasswordEmail");
			return mvc;
		}
		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Fill the form correctly");
			mvc.setViewName("redirect:/users/"+username+"/config/changePasswordEmail");
			return mvc;
		}
		if (!changePasswordEmailForm.getNewPassword().equals(changePasswordEmailForm.getConfirmNewPassword())) {
			notifyService.addDangerMessage("Password Must Match");
			mvc.setViewName("redirect:/users/"+username+"/config/changePasswordEmail");
			return mvc;
		}
		user.setPasswordHash(passwordEncoder.encode(changePasswordEmailForm.getNewPassword()));
		userService.edit(user);
		notifyService.addSuccessMessage("Password changed with success");
		mvc.setViewName("redirect:/users/"+username+"/config");
		return mvc;
	}
	
}
