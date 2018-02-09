package com.arthurzera.social.media.models;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String tag;

	@Column
	private String description;

	@ManyToMany
	private Set<Post> posts;

	public Tag() {
	}

	public Tag(String tag) {
		this.tag = tag;
	}

	public Tag(String tag, String description) {
		this.tag = tag;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		this.posts.add(post);
		post.getTags().add(this);
	}

	public void removePost(Post post) {
		this.posts.remove(post);
		post.getTags().remove(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
