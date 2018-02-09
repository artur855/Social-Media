package com.arthurzera.social.media.models;

import javax.persistence.*;

@Entity
@Table
public class PostEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, targetEntity = User.class)
	private User postEvaluator;

	@ManyToOne(optional = false, targetEntity = Post.class)
	private Post postEvaluated;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private EvaluationType evaluation;

	public PostEvaluation() {
	}

	public PostEvaluation(User postEvaluator, Post postEvaluated, EvaluationType evaluation) {
		this.postEvaluator = postEvaluator;
		this.postEvaluated = postEvaluated;
		this.evaluation = evaluation;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return postEvaluator;
	}

	public void setUser(User user) {
		this.postEvaluator = user;
	}

	public Post getPost() {
		return postEvaluated;
	}

	public void setPost(Post post) {
		this.postEvaluated = post;
	}

	public EvaluationType getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationType evalution) {
		this.evaluation = evalution;
	}

}
