package com.proptiger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Imagetype_Id")
	private int imageTypeId;
	
	@Column(name="type")
	private String type;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="type",insertable = false,updatable = false)
	private ImageType imageType; */
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="probability")
	private double probability;
	
	@Column(name="tag")
	private String tag;

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

	public double getProbability() {
		return probability;
	}

	public void setProbability(double d) {
		this.probability = d;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
}
