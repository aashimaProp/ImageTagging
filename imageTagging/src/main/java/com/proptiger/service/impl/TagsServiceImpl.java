package com.proptiger.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.proptiger.model.Images;
import com.proptiger.model.Tags;
import com.proptiger.repo.TagsDao;
import com.proptiger.service.TagsService;

@Service
public class TagsServiceImpl implements TagsService {

	private static final String API_ID = "api.id"; // "Te9XxJTbMqebovrmHvWFWplwhOVeDgIUb2jrs9J3";
	private static final String API_SECRET = "api.secret"; // "ciHTkvIfaBCFcEKoaY3Kuja0WLzB0ZSJ9oh1mVXr";
	private static final String DOMAIN = "domain";

	@Autowired
	TagsDao tagsDao;

	@Resource
	private Environment env;

	public void generateOneByOne(Images img) {
		System.out.println(img.getPath());
		String path = env.getRequiredProperty(DOMAIN) + img.getPath() + img.getSeoName();// "http://content.makaan-ws.com/"

		ClarifaiClient clarifai = new ClarifaiClient(env.getRequiredProperty(API_ID),
				env.getRequiredProperty(API_SECRET));

		System.out.println(path);
		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(path));

		Tags tags = null;
		for (Tag tag : results.get(0).getTags()) {
			tags = new Tags();
			tags.setType(img.getImageType().getType());
			tags.setImageTypeId(img.getId());
			tags.setTag(tag.getName());
			tags.setProbability(tag.getProbability());
			tagsDao.save(tags);
		}

	}

	public void generateTags(List<Images> ret) {

		System.out.println(ret);

		for (ListIterator<Images> iter = ret.listIterator(); iter.hasNext();) {

			Images img = iter.next();
			generateOneByOne(img);

		}
	}

	public String findCategory(String path) {
		ClarifaiClient clarifai = new ClarifaiClient(env.getRequiredProperty(API_ID),
				env.getRequiredProperty(API_SECRET));

		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(
				// "http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2016/2/8/0/Linc-Thelen_Kitchen-Dining_24.jpg.rend.hgtvcom.966.644.jpeg"));
				// "https://im.proptiger.com/1/500773/81/supertech-capetown-main-other-670076.jpeg"));
				// "https://im.proptiger.com/1/658039/81/3c-four-seasons-main-other-602406.jpeg"));
				"http://cgi.golivingroom.com/images/decozt.com/wp-content/uploads/2014/10/besf-of-ideas-dark-grey-color-and-orange-wall-paint-with-white-sofa-also-black-round-low-table-also-carpet-also-white-flooring-tile-in-modern-home-living-room-cool-room-designs-ideas-in-modern-contempora.jpg"));

		double[] category = { 0, 0, 0, 0 };
		int count = 1;
		for (Tag tag : results.get(0).getTags()) {
			if (count <= 15) {
				String temp = tag.getName();
				System.out.println(temp);

				int pref = -1;
				if (temp.equals("stove") || temp.equals("microwave"))
					pref = 3;
				else if (temp.equals("sofa") || temp.equals("coffee table") || temp.equals("living room"))
					pref = 0;

				List<Tags> list = tagsDao.findByTag(temp);
				// System.out.println("list " + list);
				for (int i = 0; i < list.size(); i++) {
					String val = list.get(i).getType();
					// System.out.println("val "+val);
					if (list.get(i).getProbability() > 0.9) {

						int id = 0;

						if (val.equals("bedroom")) {
							// System.out.println("bedroom if");
							id = 0;
							category[id] += (530.0 - 92.0) / 530.0;
						} else if (val.equals("bathroom")) {
							// System.out.println("bathroom if");
							id = 1;
							category[id] += (530.0 - 179.0) / 530.0;
						} else if (val.equals("dining")) {
							// System.out.println("dining if");
							id = 2;
							category[id] += (530.0 - 136.0) / 530.0;
						} else if (val.equals("kitchen")) {
							// System.out.println("kitchen if");
							id = 3;
							category[id] += (530.0 - 123.0) / 530.0;
						}
					}

					if (pref != -1)
						category[pref] += 1;

					// System.out.println("id " + id);
					// category[id] += 1;
				}
				count++;

			}
		}
		double value = category[0];
		int pos = 0;
		System.out.println("i 0 " + category[0]);
		for (int i = 1; i < 4; i++) {
			System.out.println("i " + i + category[i]);
			if (category[i] > value) {
				value = category[i];
				pos = i;
			}
		}
		Arrays.sort(category);
		System.out.println("Hello " + value + " " + pos + category);

		if (pos == 0)
			return "Choice 1: bedroom; Choice 2:living room Choice 3: Dining";
		else if (pos == 1)
			return "Choice 1: bathroom; Choice 2:kitchen";
		else if (pos == 2)
			return "Choice 1: Dining; Choice 2:living room Choice 3: Bedroom";
		else if (pos == 3)
			return "Choice 1: kitchen; Choice 2:Bathroom";
		

		return "";

	}

	public String findCategory2(String path) {
		ClarifaiClient clarifai = new ClarifaiClient(env.getRequiredProperty(API_ID),
				env.getRequiredProperty(API_SECRET));

		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(
				"http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2012/1/3/0/DH2012_Entertainment-Deck-Fire-Pit2_s4x3.jpg.rend.hgtvcom.966.725.jpeg"));
				//"http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2013/11/15/5/DP_Lauren-Levant-Bland-mixed-color-arts-and-crafts-kitchen_v.jpg.rend.hgtvcom.966.1288.jpeg"));
				//"http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2015/12/15/0/LAttitude-Design_Shores-Unit-1509_13.jpg.rend.hgtvcom.966.644.jpeg"));
				//"http://hgtvhome.sndimg.com/content/dam/images/hgrm/fullset/2013/6/27/0/DP_Jennifer-Jones-white-blue-modern-bathroom_s4x3.jpg.rend.hgtvcom.1280.960.jpeg"));
				//"http://porch.com/advice/wp-content/uploads/2015/02/NB-Design-Group-kitchen-wall-decor.jpg"));
				//"http://www.paulbarrow-kitchens.co.uk/wp-content/uploads/2010/04/durr-kitchen-photos-051_1.jpg"));
				// "http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2016/2/8/0/Linc-Thelen_Kitchen-Dining_24.jpg.rend.hgtvcom.966.644.jpeg"));
				 //"https://im.proptiger.com/1/500773/81/supertech-capetown-main-other-670076.jpeg"));
				// "https://im.proptiger.com/1/658039/81/3c-four-seasons-main-other-602406.jpeg"));
				// "http://cgi.golivingroom.com/images/decozt.com/wp-content/uploads/2014/10/besf-of-ideas-dark-grey-color-and-orange-wall-paint-with-white-sofa-also-black-round-low-table-also-carpet-also-white-flooring-tile-in-modern-home-living-room-cool-room-designs-ideas-in-modern-contempora.jpg"));
				//"http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2011/11/15/1/original_Vasi-Ypsilantis-kitchen_s4x3.jpg.rend.hgtvcom.1280.960.jpeg"));
		
		double[] category = { 0, 0, 0, 0 };
		int count = 1;
		for (Tag tag : results.get(0).getTags()) {

			String temp = tag.getName();
			System.out.println(temp);

			int pref = -1;
			if (temp.equals("stove") || temp.equals("microwave") || temp.equals("shelf")  )
				pref = 3;
			else if (temp.equals("sofa") || temp.equals("coffee table") || temp.equals("living room") || temp.equals("dining room") || temp.equals("vase"))
				pref = 2;
			else if (temp.equals("bedroom") || temp.equals("bed"))
				pref = 0;
			else if (temp.equals("bathroom") || temp.equals("bathtub") || temp.equals("lavatory"))
				pref = 1;

			int[] flag = { 0, 0, 0, 0 };
			List<Tags> list = tagsDao.findByTag(temp);
			for (int i = 0; i < list.size(); i++) {
				String val = list.get(i).getType();
				// System.out.println("value " + val);

				int id;
				if (val.equals("bedroom")) {
					// System.out.println("bedroom if");
					id = 0;
					flag[id] = 1;
				} else if (val.equals("bathroom")) {
					// System.out.println("bathroom if");
					id = 1;
					flag[id] = 1;
				} else if (val.equals("dining")) {
					// System.out.println("dining if");
					id = 2;
					flag[id] = 1;
				} else if (val.equals("kitchen")) {
					// System.out.println("kitchen if");
					id = 3;
					flag[id] = 1;
				}

			}

			if (pref != -1)
				category[pref] += 1;

			for (int j = 0; j < 4; j++) {
				System.out.println("j " + j + flag[j]);

				if (flag[j] == 1)
					category[j]++;
			}
		}
		double value = category[0];
		int pos = 0;
		System.out.println("i 0 " + category[0]);
		for (int i = 1; i < 4; i++) {
			System.out.println("i " + i + category[i]);
			if (category[i] > value) {
				value = category[i];
				pos = i;
			}
		}
		if (pos == 0)
			return "Choice 1: bedroom; Choice 2:living room Choice 3: Dining";
		else if (pos == 1)
			return "Choice 1: bathroom; Choice 2:kitchen";
		else if (pos == 2)
			return "Choice 1: Dining; Choice 2:living room Choice 3: Bedroom";
		else if (pos == 3)
			return "Choice 1: kitchen; Choice 2:Bathroom";

		return "";

	}
}
