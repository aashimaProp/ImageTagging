package com.proptiger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proptiger.model.ImageType;

@Repository
public interface ImageTypeDao extends JpaRepository<ImageType, Integer> {

}
