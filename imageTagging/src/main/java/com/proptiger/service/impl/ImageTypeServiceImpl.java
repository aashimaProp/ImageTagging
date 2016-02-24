package com.proptiger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proptiger.repo.ImageTypeDao;
import com.proptiger.service.ImageTypeService;

@Service
public class ImageTypeServiceImpl implements ImageTypeService{
	
	@Autowired
	ImageTypeDao imageTypeDao;
	
	
}
