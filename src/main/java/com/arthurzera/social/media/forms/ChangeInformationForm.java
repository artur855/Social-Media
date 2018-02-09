package com.arthurzera.social.media.forms;

import org.springframework.web.multipart.MultipartFile;

public class ChangeInformationForm {

	
	private MultipartFile profilePicture;

	private String aboutMe;
	
	private String cellphoneNumber;

	public ChangeInformationForm() {
	}

	public ChangeInformationForm(String aboutMe, String cellphoneNumber) {
		this.aboutMe = aboutMe;
		this.cellphoneNumber = cellphoneNumber;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}



}
