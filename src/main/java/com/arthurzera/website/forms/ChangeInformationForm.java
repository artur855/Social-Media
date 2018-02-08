package com.arthurzera.website.forms;

import javax.validation.constraints.Size;

public class ChangeInformationForm {

	@Size(min=0)
	private String pictureUrl;

	private String aboutMe;
	
	private String cellphoneNumber;

	public ChangeInformationForm() {
	}

	public ChangeInformationForm(String pictureUrl, String aboutMe, String cellphoneNumber) {
		this.pictureUrl = pictureUrl;
		this.aboutMe = aboutMe;
		this.cellphoneNumber = cellphoneNumber;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
