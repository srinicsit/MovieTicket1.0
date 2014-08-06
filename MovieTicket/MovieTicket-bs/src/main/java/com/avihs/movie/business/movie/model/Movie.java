package com.avihs.movie.business.movie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.Certificate;
import com.avihs.movie.business.model.Language;

@Entity
@Table(name = "MOVIE")
@DynamicUpdate
@DynamicInsert
@NamedQueries({
		@NamedQuery(name = "getMovieId", query = "select m.id from Movie m where m.movieName=:movieName "),
		@NamedQuery(name = "getMovieIdForUpdate", query = "select m.id from Movie m where m.movieName=:movieName and m.id!=:id") })
@JsonSerialize(include = Inclusion.NON_NULL)
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

	@JoinColumn(name = "LANGUAGE_ID")
	@ManyToOne
	private Language language;

	@Column(name = "RELEASE_DATE")
	private Date releaseDate;

	@Column(name = "DIRECTOR")
	private String director;

	// Drama, Thriller
	@Column(name = "GENRE")
	private String genre;

	@JoinColumn(name = "CERTIFICATE_ID")
	@ManyToOne
	private Certificate certificate;

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

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

}
