package com.arthurzera.social.media.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Post post;

	@ManyToOne
	private User user;

	@Column
	private Date createdAt;

	@Column
	private String body;

	@OneToMany(mappedBy = "parent")
	private Set<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "parent_comment")
	private Comment parent;
	
	@OneToMany(mappedBy="commentEvaluated", targetEntity=CommentEvaluation.class)
	private List<CommentEvaluation> evaluations;

	public Comment() {
	}

	public Comment(String body, User user, Post post) {
		this.body = body;
		this.user = user;
		this.post = post;
		this.createdAt = new Date();
		this.comments = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", createdAt=" + createdAt + ", body=" + body + ", parent=" + parent + "]";
	}

	public String getTimePassed() {
		Date now = new Date();
		long time = now.getTime() - this.createdAt.getTime();
		time = (long) (time / 1000.0);
		String distance = "Updated ";
		if (time < 3600) {
			distance += (int) time / 60;
			distance += ((time / 60) > 1) ? " mins ago" : " min ago";
		} else if (3600 < time && time < 86400) {
			distance += (int) time / 3600;
			distance += ((time / 3600) > 1) ? " hours ago" : " hour ago";
		} else {
			distance += (int) time / 86400;
			distance += ((time / 86400) > 1) ? " days ago" : " day ago";

		}
		return distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreatedAt() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(createdAt);
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void respondComment(Comment comment) {
		this.comments.add(comment);
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public int getPoints() {
		int points = 0;
		for (CommentEvaluation commentEvaluation : evaluations) {
			points+= commentEvaluation.getEvalution().getValue();
		}
		return points;
	}



}
