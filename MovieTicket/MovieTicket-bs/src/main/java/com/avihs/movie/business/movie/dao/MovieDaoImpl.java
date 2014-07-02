package com.avihs.movie.business.movie.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;

@Repository
public class MovieDaoImpl extends CommonDaoImpl implements MovieDao {

	public boolean isMovieExists(String movieName) {

		Query query = getCurrentSession().getNamedQuery("getMovieId");
		query.setString("movieName", movieName);
		return query.list().size() > 0;
	}

	public boolean isMovieExists(String movieName, Integer movieId) {

		Query query = getCurrentSession().getNamedQuery("getMovieIdForUpdate");
		query.setString("movieName", movieName);
		query.setInteger("id", movieId);
		return query.list().size() > 0;
	}
}
