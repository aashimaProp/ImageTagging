package com.proptiger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proptiger.model.Images;
import com.proptiger.repo.ImageDao;
import com.proptiger.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDao imageDao;

	public List<Images> getFromExisting() {
		List<Images> list = null;
		list = imageDao.findAll();
		return list;
	}

}
