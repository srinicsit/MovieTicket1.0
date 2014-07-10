package com.avihs.movie.business.theater.service;

import java.util.List;

import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtService {

	public void create(Theater theater);

	public void update(Theater theater);

	public boolean isTheaterExists(String theaterName, Integer locationId);

	public boolean isTheaterExists(Integer theaterId, String theaterName,
			Integer locationId);

	public List<Theater> getTheaters(Integer locationId);

	public List<Theater> getTheatersForUser(Integer userPkId);

	public Theater getTheater(Integer theaterPkId);

	public void delete(Theater theater);

	public Theater loadTheater(Integer id);

	public List<Theater> getTheaters(Integer userPkId, Integer locationId,
			String partialName);
}
