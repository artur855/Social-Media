package com.arthurzera.website.services;

import java.util.List;
import com.arthurzera.website.models.UserEmail;


public interface IUserEmailService {
	List<UserEmail> findAll();
	UserEmail findById(Long id);
	UserEmail findByEmail(String email);
	UserEmail create(UserEmail userEmail);
	UserEmail edit(UserEmail userEmail);
	void delete(Long id);
}
