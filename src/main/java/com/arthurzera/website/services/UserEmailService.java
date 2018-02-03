package com.arthurzera.website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurzera.website.models.UserEmail;
import com.arthurzera.website.repositories.UserEmailRepository;

@Service
public class UserEmailService implements IUserEmailService{

	@Autowired
	private UserEmailRepository userEmailRepository;
	@Override
	public List<UserEmail> findAll() {
		return userEmailRepository.findAll();
	}

	@Override
	public UserEmail findById(Long id) {
		return userEmailRepository.findOne(id);
	}

	@Override
	public UserEmail findByEmail(String email) {
		return userEmailRepository.findByEmail(email);
	}

	@Override
	public UserEmail create(UserEmail userEmail) {
		return userEmailRepository.save(userEmail);
	}

	@Override
	public UserEmail edit(UserEmail userEmail) {
		return userEmailRepository.save(userEmail);
	}

	@Override
	public void delete(Long id) {
		userEmailRepository.delete(id);
	}

}
