package com.avihs.movie.web.screen.form;

import com.avihs.movie.business.theater.model.Theater;

public class ScreenForm {

	private String location="Guntur";

	private String name;

	private Theater theater;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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
