package com.avihs.movie.business.theater.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.theater.model.Theater;

@Repository
public class TheaterMgmtDaoImpl extends CommonDaoImpl<Theater> implements
		TheaterMgmtDao<Theater> {

	public TheaterMgmtDaoImpl() {
		System.out.println("TheaterMgmtDaoImpl");
	}

	public boolean isTheaterExists(String theaterName, String location) {
		Query query = getCurrentSession().getNamedQuery("findByTheaterName");
		query.setString("name", theaterName);
		query.setString("location", location);
		return !query.list().isEmpty();

	}

	public boolean isTheaterExists(Integer id, String theaterName,
			String location) {
		Query query = getCurrentSession().getNamedQuery(
				"findByTheaterNameAndNotID");
		query.setString("name", theaterName);
		query.setInteger("id", id);
		query.setString("location", location);
		return !query.list().isEmpty();

	}

}
