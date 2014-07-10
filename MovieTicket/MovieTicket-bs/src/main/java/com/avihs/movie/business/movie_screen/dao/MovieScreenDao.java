package com.avihs.movie.business.movie_screen.dao;

import java.util.Date;
import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

public interface MovieScreenDao extends CommonDao {

	public List<MovieScreen> getMovieScreens(Integer movieId);

	public List<MovieScreen> getMovieScreens(Integer movieId, Date showDate);
}
