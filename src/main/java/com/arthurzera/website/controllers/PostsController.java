package com.arthurzera.website.controllers;

import com.arthurzera.website.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostsController extends BasicController {

	@RequestMapping("/posts/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		Post post = postService.findById(id);
		ModelAndView mvc = super.mvc();
		if (post == null) {
			notifyService.addDangerMessage("Cannot find post #" + id);
			mvc.setViewName("index");
			return mvc;
		}
		mvc.addObject("post", post);
		notifyService.addSuccessMessage("Post #" + id + " found");
		mvc.setViewName("posts/view");
		return mvc;
	}

}
