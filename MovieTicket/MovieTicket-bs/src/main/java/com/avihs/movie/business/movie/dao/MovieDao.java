package com.avihs.movie.business.movie.dao;

import com.avihs.movie.business.dao.CommonDao;

public interface MovieDao extends CommonDao {

	public boolean isMovieExists(String movieName);

	public boolean isMovieExists(String movieName, Integer movieId);

}
