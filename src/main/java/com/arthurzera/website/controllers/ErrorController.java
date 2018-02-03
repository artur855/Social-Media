package com.arthurzera.website.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ModelAndView error404() {
		ModelAndView mvc = super.mvc();
		mvc.setViewName("error/404");
		return mvc;
	}
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView error500() {
		ModelAndView mvc = super.mvc();
		mvc.setViewName("error/500");
		return mvc;
	}
	
}
