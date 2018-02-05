package com.arthurzera.website.controllers;

import com.arthurzera.website.forms.CommentForm;
import com.arthurzera.website.forms.PostForm;
import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostsController extends BasicController {

	@RequestMapping("/posts/{id}/view")
	public ModelAndView view(@PathVariable("id") Long id) {
		Post post = postService.findById(id);
		ModelAndView mvc = super.mvc();
		if (post == null) {
			notifyService.addDangerMessage("Cannot find post #" + id);
			mvc.setViewName("index");
			return mvc;
		}
		mvc.addObject("post", post);
		mvc.addObject("commentForm", new CommentForm());
		mvc.setViewName("posts/view");
		return mvc;
	}

	@RequestMapping("/posts/{id}/delete")
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mvc = super.mvc();
		postService.deleteById(id);
		mvc.setViewName("redirect:/");
		return mvc;
	}

	@RequestMapping(value = "/posts/create", method = RequestMethod.POST)
	public ModelAndView create(PostForm postForm, BindingResult bindingResult) {
		ModelAndView mvc = super.mvc();
		if (bindingResult.hasErrors()) {
			notifyService.addDangerMessage("Fill the form correctly");
			mvc.setViewName("redirect:/");
			return mvc;
		}
		if (postForm.getBody().equals("")) {
			notifyService.addDangerMessage("Body is empty");
			mvc.setViewName("redirect:/");
			return mvc;
		}
		User currentUser = (User) mvc.getModel().get("currentUser");
		postService.create(new Post(postForm.getTitle(), postForm.getBody(), currentUser));
		mvc.setViewName("redirect:/");
		return mvc;
	}

}
