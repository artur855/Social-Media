package com.arthurzera.website.models;

import javax.persistence.*;

@Entity
@Table
public class PostEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, targetEntity = User.class)
	private User evaluator;

	@ManyToOne(optional = false, targetEntity = Post.class)
	private Post evaluated;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private EvaluationType evalution;

	public PostEvaluation() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return evaluator;
	}

	public void setUser(User user) {
		this.evaluator = user;
	}

	public Post getPost() {
		return evaluated;
	}

	public void setPost(Post post) {
		this.evaluated = post;
	}

	public EvaluationType getEvalution() {
		return evalution;
	}

	public void setEvalution(EvaluationType evalution) {
		this.evalution = evalution;
	}

}
