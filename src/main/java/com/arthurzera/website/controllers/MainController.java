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
		List<Post> latest5Posts = postService.findLatest5();
		mvc.addObject("latest5posts", latest5Posts);
		List<Post> latest3Posts = latest5Posts.stream().limit(3).collect(Collectors.toList());
		mvc.addObject("latest3posts", latest3Posts);
		mvc.setViewName("index");
		return mvc;
	}

}
