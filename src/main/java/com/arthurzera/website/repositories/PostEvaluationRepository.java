package com.arthurzera.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.PostEvaluation;
import com.arthurzera.website.models.User;

@Repository
public interface PostEvaluationRepository extends JpaRepository<PostEvaluation, Long>{
	PostEvaluation findByPostEvaluated(Post postEvaluated);
	PostEvaluation findByPostEvaluator(User postEvaluator);
}
