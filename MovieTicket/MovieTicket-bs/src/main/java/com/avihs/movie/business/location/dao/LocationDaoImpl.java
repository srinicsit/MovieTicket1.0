package com.avihs.movie.business.location.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.location.model.Location;

@Repository
public class LocationDaoImpl extends CommonDaoImpl implements LocationDao {

	@Override
	public List<Location> getLocations(String partialLoc) {
		Criteria criteria = getCurrentSession().createCriteria(Location.class);
		criteria.add(Restrictions.like("name", partialLoc, MatchMode.ANYWHERE));

		return criteria.list();
	}
}
