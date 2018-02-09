package com.arthurzera.social.media.forms;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;

public class PostForm {

	@Size(min = 0, max = 30, message = "The title can't have more than 30 characters")
	private String title;
	
	@Size(min = 1, message="Body can't have less than 1 character")
	private String body;
	
	private List<String> tags = new ArrayList<>();

	public PostForm() {
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
