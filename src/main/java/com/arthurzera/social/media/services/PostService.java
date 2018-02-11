package com.arthurzera.social.media.services;

import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.Tag;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.repositories.PostRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return this.postRepository.findAll();
	}
	@Override
	public List<Post> findTimeLine(){		
		List<Post> timeLine = this.postRepository.findAll();
		Collections.reverse(timeLine);
		return timeLine;
	}

	@Override
	public List<Post> findTimeLine(User currentUser) {
		List<User> users = currentUser.getFollowing();
		users.add(currentUser);
		List<Post> timeLine = new ArrayList<>();
		users.stream().forEach(user -> user.getPosts().stream().forEach(post -> timeLine.add(post)));
		Collections.reverse(timeLine);
		return timeLine;
	}
	
	@Override
	public List<Post> findMostPopular(){
		List<Post> mostPopular = this.postRepository.findAll();
		mostPopular.sort(Comparator.comparingInt(p->p.getPoints()));
		return mostPopular;
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
