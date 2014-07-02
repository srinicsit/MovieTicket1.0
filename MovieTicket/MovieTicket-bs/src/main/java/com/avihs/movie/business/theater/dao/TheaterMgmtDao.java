package com.avihs.movie.business.theater.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtDao extends CommonDao {

	public boolean isTheaterExists(String theaterName, Integer locationId);

	public boolean isTheaterExists(Integer theaterId, String theaterName,
			Integer locationId);

	public List<Theater> getTheaters(Integer locationId);

	public List<Theater> getTheatersForUser(Integer userPkId);

	public List<Theater> getTheaters(Integer userPkId, Integer locationId,
			String partialName);

}
