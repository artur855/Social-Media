package com.arthurzera.social.media.controllers;

import com.arthurzera.social.media.forms.CommentForm;
import com.arthurzera.social.media.forms.PostForm;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.Tag;
import com.arthurzera.social.media.models.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends BasicController {

	@RequestMapping({ "/", "/index" })
	public ModelAndView index() {
		ModelAndView mvc = super.mvc();
		List<Post> allPosts = null;
		if (mvc.getModel().containsKey("currentUser")) {
			User currentUser = (User) mvc.getModel().get("currentUser");
			allPosts = postService.findTimeLine(currentUser);
		} else {
			allPosts = postService.findTimeLine();
		}
		List<Tag> tags = tagService.findAll();
		mvc.addObject("mostPopular", postService.findMostPopular());
		mvc.addObject("postForm", new PostForm());
		mvc.addObject("allPosts", allPosts);
		mvc.addObject("tags", tags);
		mvc.addObject("commentForm", new CommentForm());
		mvc.setViewName("index");
		return mvc;
	}

}
