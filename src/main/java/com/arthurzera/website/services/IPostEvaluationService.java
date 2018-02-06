package com.arthurzera.website.services;

import java.util.List;

import com.arthurzera.website.models.PostEvaluation;

public interface IPostEvaluationService {

	List<PostEvaluation> findAll();
	
	List<PostEvaluation> getUpVoted();

	List<PostEvaluation> getDownVoted();

	PostEvaluation findById(Long id);

	PostEvaluation create(PostEvaluation postEvaluation);

	PostEvaluation edit(PostEvaluation postEvaluation);

	void deleteById(Long id);
}
