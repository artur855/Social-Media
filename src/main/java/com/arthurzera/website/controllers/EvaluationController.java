package com.arthurzera.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.models.EvaluationType;
import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.PostEvaluation;
import com.arthurzera.website.models.User;

@Controller
public class EvaluationController extends BasicController {

	@RequestMapping("/posts/{id}/upvote")
	public ModelAndView upvote(@PathVariable("id") Long id) {
		ModelAndView mvc = super.mvc();
		Post post = postService.findById(id);
		User currentUser = (User) mvc.getModel().get("currentUser");
		PostEvaluation postEvaluation = postEvaluationService.findByPostEvaluatedAndPostEvaluator(post, currentUser);
		if (postEvaluation != null) {
			if (postEvaluation.getEvaluation().name().equals("UP")) {
				postEvaluation.setEvaluation(EvaluationType.NONE);
			} else {
				postEvaluation.setEvaluation(EvaluationType.UP);
			}
			postEvaluationService.edit(postEvaluation);
		} else {
			postEvaluation = new PostEvaluation(currentUser, post, EvaluationType.UP);
			postEvaluationService.create(postEvaluation);
		}
		mvc.setViewName("redirect:/");
		return mvc;
	}

	@RequestMapping("/posts/{id}/downvote")
	public ModelAndView downvote(@PathVariable("id") Long id) {
		ModelAndView mvc = super.mvc();
		Post post = postService.findById(id);
		User currentUser = (User) mvc.getModel().get("currentUser");
		PostEvaluation postEvaluation = postEvaluationService.findByPostEvaluatedAndPostEvaluator(post, currentUser);
		if (postEvaluation != null) {
			if (postEvaluation.getEvaluation().name().equals("DOWN")) {
				postEvaluation.setEvaluation(EvaluationType.NONE);
			} else {
				postEvaluation.setEvaluation(EvaluationType.DOWN);
			}
			postEvaluationService.edit(postEvaluation);
		} else {
			postEvaluation = new PostEvaluation(currentUser, post, EvaluationType.DOWN);
			postEvaluationService.create(postEvaluation);
		}
		mvc.setViewName("redirect:/");
		return mvc;
	}

}
