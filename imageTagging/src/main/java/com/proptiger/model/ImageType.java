package com.proptiger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ImageType")
public class ImageType {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="ObjectType_id")
	private int objectTypeId;
	
	@Column(name="doaminId")
	private int domainId;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(int objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
