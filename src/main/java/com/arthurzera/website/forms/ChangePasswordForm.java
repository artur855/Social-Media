package com.arthurzera.website.forms;

import com.arthurzera.website.validators.ValidPassword;

public class ChangePasswordForm {

	@ValidPassword
	private String password;

	@ValidPassword
	private String newPassword;

	@ValidPassword
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
