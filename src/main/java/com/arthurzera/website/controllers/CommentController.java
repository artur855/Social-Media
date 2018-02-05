package com.arthurzera.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.forms.CommentForm;
import com.arthurzera.website.models.User;

@Controller
public class CommentController extends BasicController{

	
	@RequestMapping(value="/comments/create", method=RequestMethod.POST)
	public ModelAndView createComment(CommentForm commentForm, BindingResult bindingResult) {
		ModelAndView mvc = super.mvc();
		User currentUser = (User) mvc.getModel().get("currentUser");
		if (currentUser== null) {
			notifyService.addDangerMessage("Need to login to make comment");
			mvc.setViewName("redirect:/users/login");
			return mvc;
		}
		return mvc;
	}
	
}
