package com.arthurzera.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.PostEvaluation;
import com.arthurzera.social.media.models.User;

@Repository
public interface PostEvaluationRepository extends JpaRepository<PostEvaluation, Long>{
	PostEvaluation findByPostEvaluated(Post postEvaluated);
	PostEvaluation findByPostEvaluator(User postEvaluator);
	PostEvaluation findByPostEvaluatedAndPostEvaluator(Post postEvaluated, User postEvaluator);
}
