package com.arthurzera.website.controllers.user.config;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arthurzera.website.controllers.BasicController;
import com.arthurzera.website.forms.ChangeInformationForm;
import com.arthurzera.website.models.User;

@Controller
public class UserConfigInformation extends BasicController {
	private static final String UPLOAD_FOLDER = "C:\\Users\\Secret\\Pictures\\UPLOADED_IMGS";

	@RequestMapping("/users/{username}/config/change-information")
	public ModelAndView changeInformation(@PathVariable("username") String username) {
		ModelAndView mvc = super.mvc();
		User user =  userService.findByUsername(username);
		User currentUser = (User) mvc.getModel().get("currentUser");
		if (!user.equals(currentUser)) {
			notifyService.addDangerMessage("You can't modify another user information");
			mvc.setViewName("redirect:/users"+currentUser.getUsername()+"/config");
			return mvc;
		}
		mvc.addObject("changeInformationForm", new ChangeInformationForm(user.getProfilePictureUrl(), user.getAboutMe(), user.getCellphoneNumber()));
		mvc.setViewName("users/change-information");
		return mvc;
	}
	
	@RequestMapping(value="/users/{username}/config/change-information", method=RequestMethod.POST)
	public ModelAndView changeInformation(@PathVariable("username")String username, ChangeInformationForm changeInformationForm, BindingResult bindingResult) {
		ModelAndView mvc = super.mvc();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError().toString());
			notifyService.addDangerMessage("Fill the form correctly!");
			mvc.setViewName("redirect:/users/"+username+"/config/change-information");
			return mvc;
		}
		User user = userService.findByUsername(username);
		user.setAboutMe(changeInformationForm.getAboutMe());
		user.setProfilePictureUrl(changeInformationForm.getPictureUrl());
		user.setCellphoneNumber(changeInformationForm.getCellphoneNumber());
		mvc.setViewName("redirect:/users/"+username+"/config");
		return mvc;
	}

}
