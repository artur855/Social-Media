package com.arthurzera.website.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurzera.website.models.PostEvaluation;
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
		return postEvaluationRepository.findAll().stream().filter(postEvaluation -> postEvaluation.getEvaluation().name().equals("UP")).collect(Collectors.toList());
	}

	@Override
	public List<PostEvaluation> getDownVoted() {
		return postEvaluationRepository.findAll().stream().filter(postEvaluation -> postEvaluation.getEvaluation().name().equals("DOWN")).collect(Collectors.toList());
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

}
