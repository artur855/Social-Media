package com.arthurzera.social.media.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.services.ICommentEvaluationService;
import com.arthurzera.social.media.services.ICommentService;
import com.arthurzera.social.media.services.INotificationService;
import com.arthurzera.social.media.services.IPostEvaluationService;
import com.arthurzera.social.media.services.IPostService;
import com.arthurzera.social.media.services.IRoleService;
import com.arthurzera.social.media.services.ITagService;
import com.arthurzera.social.media.services.IUserEmailService;
import com.arthurzera.social.media.services.IUserService;
import com.arthurzera.social.media.services.mail.IEmailService;

public abstract class BasicController {

	@Autowired
	protected IEmailService emailService;

	@Autowired
	protected IUserService userService;

	@Autowired
	protected IUserEmailService userEmailService;
	
	@Autowired
	protected IPostService postService;

	@Autowired
	public IRoleService roleService;

	@Autowired
	protected INotificationService notifyService;

	@Autowired
	protected ICommentService commentService;

	@Autowired
	protected ITagService tagService;
	
	@Autowired
	protected IPostEvaluationService postEvaluationService;
	
	@Autowired
	protected ICommentEvaluationService commentEvaluationService;

	@Autowired
	protected BCryptPasswordEncoder passwordEncoder;

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
