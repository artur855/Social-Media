package com.arthurzera.website.controllers;

import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.User;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends BasicController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mvc = super.mvc();
		List<Post> allPosts = null;
		List<Post> latest5Posts = null;
		if (mvc.getModel().containsKey("currentUser")) {
			User currentUser = (User) mvc.getModel().get("currentUser");
			allPosts = userService.findTimeLine(currentUser);
			
		} else {
			allPosts = postService.findAll();
		}
		latest5Posts = postService.findLatest5();
		mvc.addObject("allPosts", allPosts);
		mvc.addObject("latest5Posts", latest5Posts);
		mvc.setViewName("index");
		return mvc;
	}

}
