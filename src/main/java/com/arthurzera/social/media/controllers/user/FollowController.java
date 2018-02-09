package com.arthurzera.social.media.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.social.media.controllers.BasicController;
import com.arthurzera.social.media.models.User;

@Controller
public class FollowController extends BasicController{

	@RequestMapping("/users/{username}/follow")
	public ModelAndView follow(@PathVariable(name="username")String username) {
		ModelAndView mvc = super.mvc();
		User currentUser = (User) mvc.getModel().get("currentUser");
		User user = userService.findByUsername(username);
		userService.follow(currentUser, user);
		mvc.setViewName("redirect:/users/"+user.getUsername()+"/profile");
		return mvc;
	}
	@RequestMapping("/users/{username}/unfollow")
	public ModelAndView unfollow(@PathVariable(name="username")String username) {
		ModelAndView mvc = super.mvc();
		User currentUser = (User) mvc.getModel().get("currentUser");
		User user = userService.findByUsername(username);
		currentUser.unfollow(user);
		mvc.setViewName("redirect:/users/"+user.getUsername()+"/profile");
		return mvc;
	}
	
	
	
}
