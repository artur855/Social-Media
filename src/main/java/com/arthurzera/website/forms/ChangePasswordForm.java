package com.arthurzera.website.forms;

import javax.validation.constraints.Size;

public class ChangePasswordForm {

	@Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
	private String password;

	@Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
	private String newPassword;

	@Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
	private String confirmNewPassword;

	public ChangePasswordForm() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
