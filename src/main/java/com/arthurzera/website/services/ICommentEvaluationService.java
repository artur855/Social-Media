package com.arthurzera.website.services;

import java.util.List;

import com.arthurzera.website.models.Comment;
import com.arthurzera.website.models.CommentEvaluation;
import com.arthurzera.website.models.User;

public interface ICommentEvaluationService {
	
	List<CommentEvaluation> findAll();
	
	List<CommentEvaluation> findUpvoted();
	
	List<CommentEvaluation> findDownvoted();
	
	CommentEvaluation findByCommentEvaluated(Comment commentEvaluated);
	
	CommentEvaluation findByCommentEvaluator(User commentEvaluator);
	
	CommentEvaluation findByCommentEvaluatedAndCommentEvaluator(Comment commentEvaluated, User commentEvaluator);
	
	CommentEvaluation create(CommentEvaluation commentEvaluation);

	CommentEvaluation edit(CommentEvaluation commentEvaluation);
	
	void deleteById(Long id);
}
