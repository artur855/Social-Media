package com.arthurzera.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController extends BasicController{

	@RequestMapping("/acess-denied")
	public ModelAndView error_403() {
		ModelAndView mvc = super.mvc();
		mvc.setViewName("error/access-denied");
		return mvc;
	}
	
	@RequestMapping("/badUser")
	public ModelAndView badUser() {
		ModelAndView mvc = super.mvc();
		mvc.setViewName("error/bad-user");
		return mvc;
	}
	
}
