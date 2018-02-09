package com.arthurzera.social.media.services;

import java.util.List;
import java.util.Set;
import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.User;

public interface ICommentService {

	List<Comment> findByUser(User user);

	Set<Comment> findResponses(Comment comment);

	Comment findById(Long id);

	Comment create(Comment comment);

	Comment edit(Comment comment);

	void deleteById(Long id);
}
