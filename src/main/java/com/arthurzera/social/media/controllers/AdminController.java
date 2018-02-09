package com.arthurzera.social.media.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends BasicController{
	
	@RequestMapping(value="/admin/home")
	public ModelAndView home() {
		ModelAndView mvc = super.mvc();
		mvc.setViewName("admin/home");
		return mvc;
	}
}
