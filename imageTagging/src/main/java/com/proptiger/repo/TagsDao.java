package com.proptiger.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proptiger.model.Tags;

@Repository
public interface TagsDao extends JpaRepository<Tags, Integer> {

	@Query(value = "SELECT t FROM Tags t where t.tag = :tag")
	public List<Tags> findByTag(@Param("tag") String tag);
	
	public List<Tags> findDistinctTypeByTag(String tag);

	

}
