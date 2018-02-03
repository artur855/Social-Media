package com.arthurzera.website.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users_email")
public class UserEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@Email
	private String email;

	@Column
	private boolean emailConfirmed;

	@Column
	private Date emailConfirmationSentOn;

	@Column
	private Date emailConfirmedOn;
	
	@OneToOne(mappedBy = "email", optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	public UserEmail() {
	}

	public UserEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getEmailConfirmationSentOn() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(emailConfirmationSentOn);
	}

	public void setEmailConfirmationSentOn(Date emailConfirmationSentOn) {
		this.emailConfirmationSentOn = emailConfirmationSentOn;
	}

	public String getEmailConfirmedOn() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(emailConfirmedOn);
	}

	public void setEmailConfirmedOn(Date emailConfirmedOn) {
		this.emailConfirmedOn = emailConfirmedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
