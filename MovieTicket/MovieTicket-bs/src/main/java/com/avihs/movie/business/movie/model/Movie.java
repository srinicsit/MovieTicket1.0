package com.avihs.movie.business.movie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;

@Entity
@Table(name = "MOVIE")
@DynamicUpdate
@DynamicInsert
@NamedQueries({
		@NamedQuery(name = "getMovieId", query = "select m.id from Movie m where m.movieName=:movieName "),
		@NamedQuery(name = "getMovieIdForUpdate", query = "select m.id from Movie m where m.movieName=:movieName and m.id!=:id") })
public class Movie extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "MOVIE_NAME")
	private String movieName;

	@Column(name = "HOURS")
	private Integer hours;

	@Column(name = "MINS")
	private Integer mins;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "RELEASE_DATE")
	private Date releaseDate;

	@Column(name = "DIRECTOR")
	private String director;

	// Drama, Thriller
	@Column(name = "GENRE")
	private String genre;

	@Column(name = "CERTIFICATE")
	private String certificate;

	@Column(name = "DIMENSION")
	private String dimension;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMins() {
		return mins;
	}

	public void setMins(Integer mins) {
		this.mins = mins;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

}
