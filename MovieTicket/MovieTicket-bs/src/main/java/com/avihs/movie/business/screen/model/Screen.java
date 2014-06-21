package com.avihs.movie.business.screen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.theater.model.Theater;

@Entity
@Table(name = "SCREEN")
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "getScreensForTheater", query = " from Screen s where s.theater=:theaterId") })
public class Screen extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "THEATER_ID")
	private Theater theater;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}
}
