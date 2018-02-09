package com.arthurzera.social.media.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurzera.social.media.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
	Tag findByTag(String tag);
	 
}
