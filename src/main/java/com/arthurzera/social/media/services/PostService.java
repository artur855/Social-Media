package com.arthurzera.social.media.services;

import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.Tag;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.repositories.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return this.postRepository.findAll();
	}

	@Override
	public List<Post> findLatest5() {
		return this.postRepository.findLatest5Posts(new PageRequest(0, 5));
	}

	@Override
	public List<Post> findByTag(Tag tag) {
		return this.postRepository.findAll().stream().filter(post -> post.hasTag(tag)).collect(Collectors.toList());
	}

	@Override
	public Post findByComments(Comment comments) {
		return postRepository.findByComments(comments);
	}

	@Override
	public Post findByAuthor(User author) {
		return postRepository.findByAuthor(author);
	}

	@Override
	public Post findById(Long id) {
		return this.postRepository.findOne(id);
	}

	@Override
	public Post create(Post post) {
		return this.postRepository.save(post);
	}

	@Override
	public Post edit(Post post) {
		return this.postRepository.save(post);
	}

	@Override
	public void deleteById(Long id) {
		this.postRepository.delete(id);
	}

}
