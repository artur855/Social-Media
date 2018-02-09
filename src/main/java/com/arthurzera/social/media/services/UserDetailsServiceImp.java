package com.arthurzera.social.media.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.arthurzera.social.media.converters.UserToUserDetails;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private UserService userService;
	private UserToUserDetails userUserDetailsConverter;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    userUserDetailsConverter = new UserToUserDetails();
	    
		return userUserDetailsConverter.convert(userService.findByUsername(username));
	}


}
