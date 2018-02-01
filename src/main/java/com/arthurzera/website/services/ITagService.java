package com.arthurzera.website.services;

import java.util.List;

import com.arthurzera.website.models.Tag;

public interface ITagService {
	List<Tag> findAll();
	Tag findByTag(String tag);
	Tag create(Tag tag);
	Tag edit(Tag tag);
	Tag findById(Long id);
	void deleteById(Long id);
}
