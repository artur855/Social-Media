package com.arthurzera.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.arthurzera.website.models.User;

import com.arthurzera.website.models.CommentEvaluation;
import com.arthurzera.website.models.EvaluationType;
import com.arthurzera.website.models.Comment;

@Controller
public class CommentEvaluationController extends BasicController {

	@RequestMapping("/comments/{id}/upvote")
	public ModelAndView upvote(@PathVariable("id") Long id) {
		ModelAndView mvc = super.mvc();
		Comment comment = commentService.findById(id);
		User currentUser = (User) mvc.getModel().get("currentUser");
		CommentEvaluation commentEvaluation = commentEvaluationService
				.findByCommentEvaluatedAndCommentEvaluator(comment, currentUser);
		if (commentEvaluation != null) {
			if (commentEvaluation.getEvalution().name().equalsIgnoreCase("UP")) {
				commentEvaluation.setEvalution(EvaluationType.NONE);
			} else {
				commentEvaluation.setEvalution(EvaluationType.UP);
			}
			commentEvaluationService.edit(commentEvaluation);
		} else {
			commentEvaluation = new CommentEvaluation(currentUser, comment, EvaluationType.UP);
			commentEvaluationService.create(commentEvaluation);
		}
		mvc.setViewName("redirect:/");
		return mvc;
	}

	@RequestMapping("/comments/{id}/downvote")
	public ModelAndView downvote(@PathVariable("id") Long id) {
		ModelAndView mvc = super.mvc();
		Comment comment = commentService.findById(id);
		User currentUser = (User) mvc.getModel().get("currentUser");
		CommentEvaluation commentEvaluation = commentEvaluationService
				.findByCommentEvaluatedAndCommentEvaluator(comment, currentUser);
		if (commentEvaluation != null) {
			if (commentEvaluation.getEvalution().name().equalsIgnoreCase("DOWN")) {
				commentEvaluation.setEvalution(EvaluationType.NONE);
			} else {
				commentEvaluation.setEvalution(EvaluationType.DOWN);
			}
			commentEvaluationService.edit(commentEvaluation);
		} else {
			commentEvaluation = new CommentEvaluation(currentUser, comment, EvaluationType.DOWN);
			commentEvaluationService.create(commentEvaluation);
		}
		mvc.setViewName("redirect:/");
		return mvc;
	}

	
}
