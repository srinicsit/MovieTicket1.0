package com.avihs.movie.web.assign_movie.form;

import java.util.Date;

public class MovieAssignForm {

	private Integer movieAssignId;

	private Integer movieId;

	private Integer screenId;

	private Date showDate;

	private Integer hours;

	private Integer mins;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public Integer getMovieAssignId() {
		return movieAssignId;
	}

	public void setMovieAssignId(Integer movieAssignId) {
		this.movieAssignId = movieAssignId;
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

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
}
