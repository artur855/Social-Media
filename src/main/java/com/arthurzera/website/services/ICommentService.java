package com.arthurzera.website.services;

import java.util.Set;

import com.arthurzera.website.models.Comment;
import com.arthurzera.website.models.User;

public interface ICommentService {

	Set<Comment> findByUser(User user);

	Set<Comment> findResponses(Comment comment);

	Comment findById(Long id);

	Comment create(Comment comment);

	Comment edit(Comment comment);

	void deleteById(Long id);
}
