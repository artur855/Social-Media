package com.arthurzera.social.media.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.social.media.controllers.BasicController;
import com.arthurzera.social.media.forms.CommentForm;

@Controller
public class ProfileController extends BasicController {

	@RequestMapping("/users/{username}/profile")
	public ModelAndView profile(@PathVariable String username) {
		ModelAndView mvc = super.mvc();
		mvc.addObject("user", userService.findByUsername(username));
		mvc.addObject("commentForm", new CommentForm());
		mvc.setViewName("users/profile");
		return mvc;
	}

}
