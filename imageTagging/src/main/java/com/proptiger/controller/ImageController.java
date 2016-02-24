package com.proptiger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proptiger.model.Images;
import com.proptiger.service.ImageService;
import com.proptiger.service.ImageTypeService;
import com.proptiger.service.TagsService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageTypeService imageTypeService;

	@Autowired
	private TagsService tagsService;

	@RequestMapping("getImages")
	@ResponseBody
	public List<Images> getImages() {
		List<Images> ret = null;
		ret = imageService.getFromExisting();
		//System.out.println(ret.get(0));
		return ret;

	}

	@RequestMapping("fetchTags")
	@ResponseBody
	public String getTags() {
		List<Images> ret = getImages();
		tagsService.generateTags(ret);
		return "Generated Successfully!";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public String find(String str)
	{
		str=tagsService.findCategory2("1.jpg");
		return str;
	}
}
