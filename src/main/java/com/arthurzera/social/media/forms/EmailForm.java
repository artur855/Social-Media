package com.arthurzera.social.media.forms;

import org.hibernate.validator.constraints.Email;

public class EmailForm {

	@Email
	private String email;

	public EmailForm() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
