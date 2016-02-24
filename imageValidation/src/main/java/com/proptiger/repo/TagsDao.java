package com.proptiger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proptiger.model.Tags;

@Repository
public interface TagsDao extends JpaRepository<Tags, Integer> {
	
}
