package com.arthurzera.social.media.services;

import java.util.List;
import com.arthurzera.social.media.models.UserEmail;


public interface IUserEmailService {
	List<UserEmail> findAll();
	UserEmail findById(Long id);
	UserEmail findByEmail(String email);
	UserEmail create(UserEmail userEmail);
	UserEmail edit(UserEmail userEmail);
	void delete(Long id);
}
