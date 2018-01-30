package com.arthurzera.website.models;

import java.util.Date;
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

	@Column(nullable = false)
	private Date date = new Date();

	public Post() {
	}

	public Post(Long id, String title, String body, User author) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		
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
}
