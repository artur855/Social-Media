package com.arthurzera.website.repositories;

import com.arthurzera.website.models.User;
import com.arthurzera.website.models.UserEmail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
   User findByEmail(UserEmail email);
   boolean existsByUsername(String username);
   boolean existsByFullName(String fullName);
   boolean existsByEmail(UserEmail email);
   
}
