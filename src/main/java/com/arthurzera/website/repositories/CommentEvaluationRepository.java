package com.arthurzera.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.website.models.Comment;
import com.arthurzera.website.models.User;
import com.arthurzera.website.models.CommentEvaluation;

@Repository
public interface CommentEvaluationRepository extends JpaRepository<CommentEvaluation, Long> {
	CommentEvaluation findByCommentEvaluated(Comment commentEvaluated);

	CommentEvaluation findByCommentEvaluator(User commentEvaluator);

	CommentEvaluation findByCommentEvaluatedAndCommentEvaluator(Comment commentEvaluated, User commentEvaluator);

}
