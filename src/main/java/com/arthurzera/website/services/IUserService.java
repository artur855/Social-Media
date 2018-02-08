package com.arthurzera.website.services;

import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.User;
import com.arthurzera.website.models.VerificationToken;
import java.util.List;

public interface IUserService {

	boolean authenticate(String username, String password);

	List<User> findAll();

	List<Post> findTimeLine(User currentUser);

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
