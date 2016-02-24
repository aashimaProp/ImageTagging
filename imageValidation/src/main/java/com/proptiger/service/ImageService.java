package com.proptiger.service;

import java.util.List;

import com.proptiger.model.Images;

public interface ImageService {

	public List<Images> getFromExisting();
	public void passOneByOne(Images item);
	public void fetchFromApi();
}
