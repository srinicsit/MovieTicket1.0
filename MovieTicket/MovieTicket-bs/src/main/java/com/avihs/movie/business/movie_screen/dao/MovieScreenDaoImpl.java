package com.avihs.movie.business.movie_screen.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Repository
public class MovieScreenDaoImpl extends CommonDaoImpl implements MovieScreenDao {

	public List<MovieScreen> getMovieScreens(Integer movieId) {

		Query query = getCurrentSession().getNamedQuery("getMovieScreens");
		query.setInteger("movieId", movieId);
		List<MovieScreen> movieScreens = query.list();

		return movieScreens;
	}

	public List<MovieScreen> getMovieScreens(Integer movieId, Date showDate) {
		Query query = getCurrentSession().getNamedQuery(
				"getMovieScreensForDate");
		query.setInteger("movieId", movieId);
		query.setDate("showDate", showDate);
		List<MovieScreen> movieScreens = query.list();
		return movieScreens;

	}
}
