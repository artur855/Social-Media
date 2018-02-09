package com.arthurzera.social.media.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arthurzera.social.media.models.Comment;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.repositories.CommentRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> findByUser(User user) {
		return user.getComments();
	}

	@Override
	public Set<Comment> findResponses(Comment comment) {
		Set<Comment> responses = new HashSet<>();
		Queue<Comment> queue = new LinkedList<>(comment.getComments());
		while (!queue.isEmpty()) {
			for (Comment c : queue) {
				if (c == null) {
					queue = new LinkedList<>();
					break;
				}
				if (responses.contains(c)) {
					queue.add(null);
				}
				responses.add(c);
				if (!c.getComments().isEmpty()) {
					queue.addAll(c.getComments());
				}

				queue.poll();
			}
		}
		return responses;
	}

	@Override
	public Comment findById(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public Comment create(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment edit(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(Long id) {
		commentRepository.delete(id);
	}

}
