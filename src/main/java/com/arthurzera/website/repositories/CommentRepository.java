package com.arthurzera.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthurzera.website.models.Comment;
import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findByUser(User user);
	Comment findByPost(Post post);
	Comment findByBody(String body);
	
	
}
