package com.proptiger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proptiger.model.Images;

@Repository
public interface ImageDao extends JpaRepository<Images, Integer> {

}
