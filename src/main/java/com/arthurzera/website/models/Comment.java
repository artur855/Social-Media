package com.arthurzera.website.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private User user;
	
	@Column
	private Date createdAt = new Date();
	
	@Column
	private String body;
	
	@OneToMany(mappedBy="parent")
	private Set<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name="parent_comment")
	private Comment parent;

	public Comment(String body, User user, Post post) {
		this.body = body;
		this.user = user;
		this.post = post;
	}
	public Comment() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getCreatedAt() {
		return createdAt;
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
	
	
	
	
	
}
