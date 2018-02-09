package com.arthurzera.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.VerificationToken;

@Repository
public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Long>{
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}
