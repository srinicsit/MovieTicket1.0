package com.avihs.movie.business.theater.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.theater.model.Theater;

@Repository
public class TheaterMgmtDaoImpl extends CommonDaoImpl<Theater> implements
		TheaterMgmtDao<Theater> {

	public TheaterMgmtDaoImpl() {
		System.out.println("TheaterMgmtDaoImpl");
	}

	@Override
	public boolean isTheaterExists(String theaterName, String location) {
		Query query = getCurrentSession().getNamedQuery("findByTheaterName");
		query.setString("name", theaterName);
		query.setString("location", location);
		return !query.list().isEmpty();

	}

	@Override
	public boolean isTheaterExists(Integer id, String theaterName,
			String location) {
		Query query = getCurrentSession().getNamedQuery(
				"findByTheaterNameAndNotID");
		query.setString("name", theaterName);
		query.setInteger("id", id);
		query.setString("location", location);
		return !query.list().isEmpty();

	}

	@Override
	public List<Theater> getTheaters(String location) {
		Query query = getCurrentSession().getNamedQuery(
				"findTheatersByLocation");
		query.setString("location", location);
		return query.list();
	}

	@Override
	public List<Theater> getTheaters(Integer userPkId) {
		Query query = getCurrentSession().getNamedQuery("findTheatersByUser");
		query.setInteger("user", userPkId);
		return query.list();
		// Criteria criteria =
		// getCurrentSession().createCriteria(Theater.class);
		// criteria.add(Restrictions.eq("user", userPkId));
		//
		// // Projection p1 = Projections.property("name");
		// // Projection p2 = Projections.property("location");
		// //
		// // criteria.setProjection(p1);
		// // criteria.setProjection(p2);
		// return criteria.list();
	}
}
