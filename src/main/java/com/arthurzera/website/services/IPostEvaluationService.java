package com.arthurzera.website.services;

import java.util.List;

import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.PostEvaluation;
import com.arthurzera.website.models.User;

public interface IPostEvaluationService {

	List<PostEvaluation> findAll();
	
	List<PostEvaluation> getUpVoted();

	List<PostEvaluation> getDownVoted();

	PostEvaluation findById(Long id);
	
	PostEvaluation findByPostEvaluator(User postEvaluator);

	PostEvaluation findByPostEvaluated(Post postEvaluated);

	PostEvaluation findByPostEvaluatedAndPostEvaluator(Post postEvaluated, User postEvaluator);
	
	PostEvaluation create(PostEvaluation postEvaluation);

	PostEvaluation edit(PostEvaluation postEvaluation);

	void deleteById(Long id);
}
