package com.avihs.movie.business.movie_screen.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.movie.model.Movie;
import com.avihs.movie.business.screen.model.Screen;

@Entity
@Table(name = "MOVIE_SCREEN")
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

	@Column(name = "SHOW_TIME")
	private Date showTime;

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

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

}
