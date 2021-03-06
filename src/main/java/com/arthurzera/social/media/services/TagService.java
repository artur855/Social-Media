package com.arthurzera.social.media.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arthurzera.social.media.models.Tag;
import com.arthurzera.social.media.repositories.TagRepository;

@Service
public class TagService implements ITagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Tag findByTag(String tag) {
		return tagRepository.findByTag(tag);
	}

	@Override
	public Tag create(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag edit(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag findById(Long id) {
		return tagRepository.findOne(id);
	}

	@Override
	public void deleteById(Long id) {
		tagRepository.delete(id);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

}
