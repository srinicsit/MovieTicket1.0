package com.avihs.movie.business.theater.dao;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtDao<Theater> extends CommonDao<Theater> {
	
	public boolean isTheaterExists(String theaterName,String location);

	public boolean isTheaterExists(Integer id, String theaterName,String location);

}
