package com.avihs.movie.business.movie_screen.model;

import java.util.Date;

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
import com.avihs.movie.business.movie.model.Movie;
import com.avihs.movie.business.screen.model.Screen;

@Entity
@Table(name = "MOVIE_SCREEN")
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "getMovieScreens", query = "from MovieScreen where movie=:movieId") })
public class MovieScreen extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "MOVIE_ID")
	@ManyToOne
	private Movie movie;

	@JoinColumn(name = "SCREEN_ID")
	@ManyToOne
	private Screen screen;

	@Column(name = "SHOW_DATE")
	private Date showDate;

	@Column(name = "SHOW_HOURS")
	private Integer showHours;

	@Column(name = "SHOW_MINS")
	private Integer showMins;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Integer getShowHours() {
		return showHours;
	}

	public void setShowHours(Integer showHours) {
		this.showHours = showHours;
	}

	public Integer getShowMins() {
		return showMins;
	}

	public void setShowMins(Integer showMins) {
		this.showMins = showMins;
	}

}
