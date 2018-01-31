package com.arthurzera.website.controllers;

import com.arthurzera.website.models.Post;

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
		List<Post> allPosts = postService.findAll();
		List<Post> latest5Posts = postService.findLatest5();
		mvc.addObject("allPosts", allPosts);
		mvc.addObject("latest5Posts", latest5Posts);
		mvc.setViewName("index");
		return mvc;
	}

}
