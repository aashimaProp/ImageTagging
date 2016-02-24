package com.proptiger.service;

import java.util.List;

import com.proptiger.model.Images;

public interface TagsService {

	public void generateOneByOne(Images img);
	public void generateTags(List<Images> ret);
	public String findCategory(String path);
	public String findCategory2(String string);

}
