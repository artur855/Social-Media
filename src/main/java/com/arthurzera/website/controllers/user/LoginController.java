package com.arthurzera.website.controllers.user;

import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.LoginForm;
import com.arthurzera.website.models.User;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BasicController {

	@RequestMapping("/users/login")
	public ModelAndView login() {
		ModelAndView mvc = super.mvc();
		mvc.addObject("loginForm", new LoginForm());
		if (mvc.getModel().get("currentUser")!=null) {
			mvc.setViewName("redirect:/");
			return mvc;
		}
		mvc.setViewName("users/login");
		return mvc;
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpSession session) {

		ModelAndView mvc = super.mvc();
		
		if (error != null) {
			notifyService.addDangerMessage(error);
			mvc.setViewName("/users/login");
			return mvc;
		}

		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Please fill the form correctly!");
			mvc.setViewName("/users/login");
			return mvc;
		}

		if (!userService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
			notifyService.addDangerMessage("Invalid username or password!");
			mvc.setViewName("/users/login");
			return mvc;
		}
		
		mvc.setViewName("redirect:/index");
		
		return mvc;
	}

	@RequestMapping("/users/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mvc = super.mvc();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		User user = (User) mvc.getModel().get("currentUser");
		user.setLastSeen(new Date());
		userService.edit(user);
		notifyService.addSuccessMessage("Logout with success");
		mvc.setViewName("redirect:/users/login?logout");
		return mvc;
	}
	
}
