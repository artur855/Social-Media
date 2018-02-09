package com.arthurzera.social.media.forms;

import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import com.arthurzera.social.media.validators.ValidPassword;

public class RegisterForm {

	@Size(min = 6, max = 15, message = "Username size should have more them 6 and less than 15 characters")
	private String username;

	@Size(min = 0, max = 40, message = "Fullname size should have more them 2 and less than 40 characters")
	private String fullName;

	@Email
	private String email;

	@ValidPassword
	private String password;

	@ValidPassword
	private String confirmPassword;

	private Date birthday;
	
	private String cellphoneNumber;

	public RegisterForm() {
	}

	@Override
	public String toString() {
		return "RegisterForm{" + "username=" + username + ", fullName=" + fullName + ", email=" + email + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", birthday="+birthday.toString()+", cellphoneNumber="+cellphoneNumber+'}';
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

}
