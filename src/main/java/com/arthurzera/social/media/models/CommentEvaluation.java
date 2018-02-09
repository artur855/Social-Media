package com.arthurzera.social.media.models;

import javax.persistence.*;

@Entity
@Table
public class CommentEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, targetEntity = User.class)
	private User commentEvaluator;

	@ManyToOne(optional = false, targetEntity = Comment.class)
	private Comment commentEvaluated;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private EvaluationType evalution;

	public CommentEvaluation() {
	}

	public CommentEvaluation(User commentEvaluator, Comment commentEvaluated, EvaluationType evaluation) {
		this.commentEvaluator = commentEvaluator;
		this.commentEvaluated = commentEvaluated;
		this.evalution = evaluation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCommentEvaluator() {
		return commentEvaluator;
	}

	public void setCommentEvaluator(User commentEvaluator) {
		this.commentEvaluator = commentEvaluator;
	}

	public Comment getCommentEvaluated() {
		return commentEvaluated;
	}

	public void setCommentEvaluated(Comment commentEvaluated) {
		this.commentEvaluated = commentEvaluated;
	}

	public EvaluationType getEvalution() {
		return evalution;
	}

	public void setEvalution(EvaluationType evalution) {
		this.evalution = evalution;
	}

}
