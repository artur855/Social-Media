package com.arthurzera.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findByUser(User user);
	Comment findByPost(Post post);
	Comment findByBody(String body);
	
	
}
