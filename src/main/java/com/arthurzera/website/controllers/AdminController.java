package com.arthurzera.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arthurzera.website.models.User;
import com.arthurzera.website.services.IUserService;

@Controller
public class AdminController extends BasicController{

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/admin/home")
	public ModelAndView home() {
		ModelAndView mvc = super.mvc();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		mvc.addObject("user", user);
		mvc.addObject("successMessage", "YOU ARE A FKUCKING ADMIN");
		mvc.setViewName("admin/home");
		return mvc;
	}
}
