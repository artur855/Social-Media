package com.arthurzera.website.controllers.user.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.models.User;

@Controller
public class UserConfigController extends BasicController {

	@RequestMapping("/users/{username}/config")
	public ModelAndView userConfig(@PathVariable("username") String username) {
		ModelAndView mvc = super.mvc();
		User user = (User) mvc.getModel().get("currentUser");
		if (!user.getUsername().equals(username)) {
			notifyService.addDangerMessage("You can't make configuration on another user account");
			mvc.setViewName("redirect:/users/" + user.getUsername() + "/config");
			return mvc;
		}
		mvc.addObject("user", user);
		mvc.setViewName("users/config");
		return mvc;
	}

}
