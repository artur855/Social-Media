package com.arthurzera.website.forms;

import org.hibernate.validator.constraints.Email;
import com.arthurzera.website.validators.ValidPassword;

public class EmailPasswordForm {
	
	@Email
	private String email;
	
	@ValidPassword
	private String password;
	
	@ValidPassword
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
