package com.arthurzera.social.media.services;

import java.util.List;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.PostEvaluation;
import com.arthurzera.social.media.models.User;

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
