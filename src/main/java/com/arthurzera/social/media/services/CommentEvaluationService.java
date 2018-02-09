package com.arthurzera.social.media.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.CommentEvaluation;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.repositories.CommentEvaluationRepository;

@Service
public class CommentEvaluationService implements ICommentEvaluationService {

	@Autowired
	private CommentEvaluationRepository commentEvaluationRepository;

	@Override
	public List<CommentEvaluation> findAll() {
		return commentEvaluationRepository.findAll();
	}

	@Override
	public List<CommentEvaluation> findUpvoted() {
		return commentEvaluationRepository.findAll().stream()
				.filter(commentEvaluation -> commentEvaluation.getEvalution().name().equalsIgnoreCase("UP"))
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentEvaluation> findDownvoted() {
		return commentEvaluationRepository.findAll().stream()
				.filter(commentEvaluation -> commentEvaluation.getEvalution().name().equalsIgnoreCase("DOWN"))
				.collect(Collectors.toList());
	}

	@Override
	public CommentEvaluation findByCommentEvaluated(Comment commentEvaluated) {
		return commentEvaluationRepository.findByCommentEvaluated(commentEvaluated);
	}

	@Override
	public CommentEvaluation findByCommentEvaluator(User commentEvaluator) {
		return commentEvaluationRepository.findByCommentEvaluator(commentEvaluator);
	}

	@Override
	public CommentEvaluation findByCommentEvaluatedAndCommentEvaluator(Comment commentEvaluated,
			User commentEvaluator) {
		return commentEvaluationRepository.findByCommentEvaluatedAndCommentEvaluator(commentEvaluated,
				commentEvaluator);
	}

	@Override
	public CommentEvaluation create(CommentEvaluation commentEvaluation) {
		return commentEvaluationRepository.save(commentEvaluation);
	}

	@Override
	public CommentEvaluation edit(CommentEvaluation commentEvaluation) {
		return commentEvaluationRepository.save(commentEvaluation);
	}

	@Override
	public void deleteById(Long id) {
		commentEvaluationRepository.delete(id);
	}

}
