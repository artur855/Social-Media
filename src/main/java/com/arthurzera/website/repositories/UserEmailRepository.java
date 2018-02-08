package com.arthurzera.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthurzera.website.models.UserEmail;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {
	UserEmail findByEmail(String email);
	UserEmail existsByEmail(String email);
}
