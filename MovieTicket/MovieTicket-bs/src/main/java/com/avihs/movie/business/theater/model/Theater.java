package com.avihs.movie.business.theater.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;

@Entity
@Table(name = "THEATER")
public class Theater extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
