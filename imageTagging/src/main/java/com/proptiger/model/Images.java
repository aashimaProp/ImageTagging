package com.proptiger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image_small1")
public class Images {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "ImageType_id")
	private int imageTypeId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ImageType_id",insertable = false,updatable = false)
	private ImageType imageType;

	@Column(name = "seo_name")
	private String seoName;

	@Column(name = "path")
	private String path;
	
	@Column(name = "priority")
	private Integer priority;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImageTypeId() {
		return imageTypeId;
	}

	public void setImageTypeId(int imageTypeId) {
		this.imageTypeId = imageTypeId;
	}

	public String getSeoName() {
		return seoName;
	}

	public void setSeoName(String seoName) {
		this.seoName = seoName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

}
