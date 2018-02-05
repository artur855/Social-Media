package com.arthurzera.website.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.arthurzera.website.models.User;
import com.arthurzera.website.models.UserEmail;
import com.arthurzera.website.services.IUserService;

@Service
public class FacebookConnectionSignUp implements ConnectionSignUp{

	@Autowired
	private IUserService userService;
	
	@Override
	public String execute(Connection<?> arg0) {
		User user = new User();
		user.setUsername(arg0.fetchUserProfile().getUsername());
		user.setEmail(new UserEmail(arg0.fetchUserProfile().getEmail()));
		user.setFullName(arg0.fetchUserProfile().getFirstName() + " " + arg0.fetchUserProfile().getLastName());
		userService.create(user);
		return user.getUsername();
	}

}
