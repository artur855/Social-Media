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
	private boolean email_confirmed;

	@Column
	private Date email_confirmation_sent_on;

	@Column
	private Date email_confirmed_on;
 
	@OneToOne(mappedBy = "email", optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;

	public UserEmail() {
	}

	public UserEmail(String email) {
		this.email= email;
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
		return email_confirmed;
	}

	public void setEmail_confirmed(boolean email_confirmed) {
		this.email_confirmed = email_confirmed;
	}

	public String getEmail_confirmation_sent_on() {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		return format.format(email_confirmation_sent_on);
	}

	public void setEmail_confirmation_sent_on(Date email_confirmation_sent_on) {
		this.email_confirmation_sent_on = email_confirmation_sent_on;
	}

	public String getEmail_confirmed_on() {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		return format.format(email_confirmed_on);
	}

	public void setEmail_confirmed_on(Date email_confirmed_on) {
		this.email_confirmed_on = email_confirmed_on;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
