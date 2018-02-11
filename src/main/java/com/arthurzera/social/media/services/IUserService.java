package com.arthurzera.social.media.services;

import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.VerificationToken;
import java.util.List;

public interface IUserService {

	boolean authenticate(String username, String password);

	List<User> findAll();

	User findById(Long id);

	User findByUsername(String username);

	User findByEmail(String email);

	User create(User user);

	User edit(User user);

	void deleteById(Long id);

	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String token);

	void follow(User currentUser, User user);

	void unfollow(User currentUser, User user);

	boolean existsByUsername(String username);

	boolean existsByFullName(String fullname);

	boolean existsByEmail(String email);

}
