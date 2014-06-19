package com.avihs.movie.business.movie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;

@Entity
@Table(name="MOVIE")
@DynamicUpdate
@DynamicInsert
public class Movie extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="MOVIE_NAME")
	private String movieName;

}
