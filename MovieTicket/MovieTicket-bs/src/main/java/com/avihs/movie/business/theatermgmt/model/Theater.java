package com.avihs.movie.business.theatermgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;

@Entity
@Table(name = "THEATER")
public class Theater extends BaseModel {

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LOCATION")
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
