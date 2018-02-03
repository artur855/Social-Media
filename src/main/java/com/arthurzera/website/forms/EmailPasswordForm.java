package com.arthurzera.website.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class EmailPasswordForm {
	
	@Email
	private String email;
	
	@Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
	private String password;
	
	@Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
	private String confirmPassword;

	public EmailPasswordForm() {
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
