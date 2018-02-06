package com.arthurzera.website.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.PostEvaluation;
import com.arthurzera.website.models.User;
import com.arthurzera.website.repositories.PostEvaluationRepository;

@Service
public class PostEvaluationService implements IPostEvaluationService{

	@Autowired
	private PostEvaluationRepository postEvaluationRepository;
	
	@Override
	public List<PostEvaluation> findAll() {
		return postEvaluationRepository.findAll();
	}

	@Override
	public List<PostEvaluation> getUpVoted() {
		return postEvaluationRepository.findAll().stream().filter(postEvaluation -> postEvaluation.getEvaluation().name().equalsIgnoreCase("UP")).collect(Collectors.toList());
	}

	@Override
	public List<PostEvaluation> getDownVoted() {
		return postEvaluationRepository.findAll().stream().filter(postEvaluation -> postEvaluation.getEvaluation().name().equalsIgnoreCase("DOWN")).collect(Collectors.toList());
	}

	@Override
	public PostEvaluation findById(Long id) {
		return postEvaluationRepository.findOne(id);
	}

	@Override
	public PostEvaluation create(PostEvaluation postEvaluation) {
		return postEvaluationRepository.save(postEvaluation);
	}

	@Override
	public PostEvaluation edit(PostEvaluation postEvaluation) {
		return postEvaluationRepository.save(postEvaluation);
	}

	@Override
	public void deleteById(Long id) {
		postEvaluationRepository.delete(id);
	}

	@Override
	public PostEvaluation findByPostEvaluator(User postEvaluator) {
		return postEvaluationRepository.findByPostEvaluator(postEvaluator);
	}

	@Override
	public PostEvaluation findByPostEvaluated(Post postEvaluated) {
		return postEvaluationRepository.findByPostEvaluated(postEvaluated);
	}

	@Override
	public PostEvaluation findByPostEvaluatedAndPostEvaluator(Post postEvaluated, User postEvaluator) {
		return postEvaluationRepository.findByPostEvaluatedAndPostEvaluator(postEvaluated, postEvaluator);
	}

}
