package com.avihs.movie.business.theater.service;

import java.util.List;

import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtService {

	public void create(Theater theater);

	public void update(Theater theater);

	public boolean isTheaterExists(String theaterName, String location);

	public boolean isTheaterExists(Integer pkId, String theaterName,
			String location);

	public List<Theater> getTheaters(String location);

	public List<Theater> getTheaters(Integer userPkId);

	public Theater getTheater(Integer theaterPkId);

	public void delete(Theater theater);

	public List<Theater> getTheaters(Integer userPkId, String location,
			String partialName);
}
