package com.arthurzera.social.media.controllers.user.config;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.social.media.controllers.BasicController;
import com.arthurzera.social.media.forms.ChangePasswordForm;
import com.arthurzera.social.media.models.User;

@Controller
public class ChangePasswordController extends BasicController {

	@RequestMapping("/users/{username}/config/change-password")
	public ModelAndView changePassword(@PathVariable("username") String username) {
		ModelAndView mvc = super.mvc();
		mvc.addObject("user", userService.findByUsername(username));
		mvc.addObject("changePasswordForm", new ChangePasswordForm());
		mvc.setViewName("users/change-password");
		return mvc;

	}

	@RequestMapping(value = "/users/{username}/config/change-password", method = RequestMethod.POST)
	public ModelAndView changePassword(@PathVariable("username") String username, ChangePasswordForm changePasswordForm,
			BindingResult bindingResult) {
		ModelAndView mvc = super.mvc();
		User user = userService.findByUsername(username);
		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Fill the form correctly");
			mvc.setViewName("redirect:/users/" + username + "/config/change-password");
			return mvc;
		}
		if (!passwordEncoder.matches(changePasswordForm.getPassword(), user.getPasswordHash())) {
			notifyService.addDangerMessage("Wrong password");
			mvc.setViewName("redirect:/users/" + username + "/config/change-password");
			return mvc;
		}
		if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmNewPassword())) {
			notifyService.addDangerMessage("Passwords must match!");
			mvc.setViewName("redirect:/users/" + username + "/config/change-password");
			return mvc;
		}
		user.setPasswordHash(passwordEncoder.encode(changePasswordForm.getNewPassword()));
		userService.edit(user);
		notifyService.addSuccessMessage("Password changed with success");
		mvc.setViewName("redirect:/users/" + username + "/config");
		return mvc;

	}

}
