package com.arthurzera.website.services;

import com.arthurzera.website.models.Comment;
import com.arthurzera.website.models.Post;
import com.arthurzera.website.models.Tag;
import com.arthurzera.website.models.User;

import java.util.List;

public interface IPostService {

	List<Post> findAll();

	List<Post> findLatest5();

	Post findById(Long id);

	Post create(Post post);

	Post edit(Post post);

	void deleteById(Long id);

	List<Post> findByTag(Tag tag);

	Post findByComments(Comment comments);

	Post findByAuthor(User author);

}
