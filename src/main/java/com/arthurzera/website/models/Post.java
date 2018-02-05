package com.arthurzera.website.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 300)
	private String title;

	@Lob
	@Column(nullable = false)
	private String body;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User author;

	@OneToMany(mappedBy="post", targetEntity=Comment.class)
	private List<Comment> comments;
	
	@Column(nullable = false)
	private Date date;
	
	@ManyToMany
	@JoinTable
	private List<Tag> tags;
	
	@OneToMany(mappedBy="postEvaluated", targetEntity=PostEvaluation.class)
	private List<PostEvaluation> evaluations;
	
	public Post() {
	}

	public Post(String title, String body, User author) {
		this.title = title;
		this.body = body;
		this.author = author;
		this.tags = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.date = new Date();
		 
	}

	@Override
	public String toString() {
		return "Post{" + "id=" + id + ", title=" + title + ", body=" + body + ", author=" + author + '}';
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTimePassed() {
		Date now = new Date();
		long time = now.getTime() - this.date.getTime();
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
		
	}
	
	public boolean hasTag(Tag tag) {
		return this.tags.contains(tag);
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getPosts().add(this);
	}
	public void removeTag(Tag tag) {
		this.tags.remove(tag);
		tag.getPosts().remove(this);
	}
 
	public int getPoints() {
		int points = 0;
		for (PostEvaluation postEvaluation : evaluations) {
			points+= postEvaluation.getEvalution().getValue();
		}
		return points;
	}
	
	
}
