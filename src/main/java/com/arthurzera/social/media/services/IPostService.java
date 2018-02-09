package com.arthurzera.social.media.services;

import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.Tag;
import com.arthurzera.social.media.models.User;

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
