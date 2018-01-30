package com.arthurzera.website.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.arthurzera.website.models.User;
import com.arthurzera.website.services.INotificationService;
import com.arthurzera.website.services.IPostService;
import com.arthurzera.website.services.IRoleService;
import com.arthurzera.website.services.IUserService;
import com.arthurzera.website.services.mail.IEmailService;

public class BasicController {

	@Autowired
	protected IEmailService emailService;
	
	@Autowired
	protected IUserService userService;

	@Autowired
	protected IPostService postService;

	@Autowired
	public IRoleService roleService;

	@Autowired
	protected INotificationService notifyService;

	public ModelAndView mvc() {
		ModelAndView mvc = new ModelAndView();
		Optional<User> currentUser = currentUser();
		if (currentUser.isPresent()) {
			mvc.addObject("currentUser", currentUser.get());
		}

		return mvc;
	}

	public Optional<User> currentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			User principal = userService.findByUsername(auth.getName());
			return Optional.of(principal);
		}
		return Optional.empty();
	}

}
