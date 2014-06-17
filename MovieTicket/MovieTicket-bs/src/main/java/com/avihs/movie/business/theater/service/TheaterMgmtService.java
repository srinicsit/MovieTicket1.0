package com.avihs.movie.business.theater.service;

import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtService {

	public void create(Theater theater);

	public boolean isTheaterExists(String theaterName,String location);

	public boolean isTheaterExists(Integer pkId, String theaterName,String location);
}
