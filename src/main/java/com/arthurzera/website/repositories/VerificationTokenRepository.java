package com.arthurzera.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthurzera.website.models.User;
import com.arthurzera.website.models.VerificationToken;

@Repository
public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Long>{
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}
