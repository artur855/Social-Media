package com.arthurzera.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.CommentEvaluation;

@Repository
public interface CommentEvaluationRepository extends JpaRepository<CommentEvaluation, Long> {
	CommentEvaluation findByCommentEvaluated(Comment commentEvaluated);

	CommentEvaluation findByCommentEvaluator(User commentEvaluator);

	CommentEvaluation findByCommentEvaluatedAndCommentEvaluator(Comment commentEvaluated, User commentEvaluator);

}
