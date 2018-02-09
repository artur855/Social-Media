package com.arthurzera.social.media.repositories;

import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.UserEmail;
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
