package com.arthurzera.social.media.forms;

import com.arthurzera.social.media.validators.ValidPassword;

public class ChangePasswordEmailForm {

	@ValidPassword
	private String newPassword;

	@ValidPassword
	private String confirmNewPassword;

	public ChangePasswordEmailForm() {
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
