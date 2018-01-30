package com.arthurzera.website.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.models.User;

@Controller
public class UserConfigController extends BasicController {
	
	@RequestMapping("/users/{username}/config")
	public ModelAndView userConfig(@PathVariable String username) {
		ModelAndView mvc = super.mvc();
		if (!((User) mvc.getModelMap().get("currentUser")).getUsername().equals(username)) {
			notifyService.addWarningMessage("You can't access the config page of other user.");
			mvc.setViewName(
					"redirect:/users/" + ((User) mvc.getModelMap().get("currentUser")).getUsername() + "/config");
		}
		User user = userService.findByUsername(username);
		mvc.addObject("user", user);
		mvc.setViewName("users/config");
		return mvc;
	}

}
