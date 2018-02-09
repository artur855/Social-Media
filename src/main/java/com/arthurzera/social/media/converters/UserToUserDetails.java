package com.arthurzera.social.media.converters;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.UserDetailsImp;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	@Override
	public UserDetails convert(User user) {
		UserDetailsImp userDetails = new UserDetailsImp();
		if (user != null) {
			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getPasswordHash());
			userDetails.setFullname(user.getFullName());
			userDetails.setEnabled(user.isEnabled());
			userDetails.setEmail(user.getEmail());
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			userDetails.setAuthorities(authorities);
		}
		return userDetails;
	}

}
