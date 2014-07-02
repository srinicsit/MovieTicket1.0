package com.avihs.movie.business.theater.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.user.model.User;

@Repository
public class TheaterMgmtDaoImpl extends CommonDaoImpl implements TheaterMgmtDao {

	public TheaterMgmtDaoImpl() {
		System.out.println("TheaterMgmtDaoImpl");
	}

	@Override
	public boolean isTheaterExists(String theaterName, Integer locationId) {
		Query query = getCurrentSession().getNamedQuery("findByTheaterName");
		query.setString("name", theaterName);
		query.setInteger("location", locationId);
		return !query.list().isEmpty();

	}

	@Override
	public boolean isTheaterExists(Integer theaterId, String theaterName,
			Integer locationId) {
		Query query = getCurrentSession().getNamedQuery(
				"findByTheaterNameAndNotID");
		query.setString("name", theaterName);
		query.setInteger("id", theaterId);
		query.setInteger("location", locationId);
		return !query.list().isEmpty();

	}

	@Override
	public List<Theater> getTheaters(Integer locationId) {
		if (locationId != null) {
			Query query = getCurrentSession().getNamedQuery(
					"findTheatersByLocation");
			query.setInteger("location", locationId);
			return query.list();
		} else {
			return new ArrayList<Theater>(0);
		}
	}

	@Override
	public List<Theater> getTheatersForUser(Integer userPkId) {
		// Query query =
		// getCurrentSession().getNamedQuery("findTheatersByUser");
		// query.setInteger("user", userPkId);
		// return query.list();
		Criteria criteria = getCurrentSession().createCriteria(Theater.class);
		User user = new User();
		user.setId(userPkId);
		criteria.add(Restrictions.eq("user", user));
		// criteria.createAlias("user", "u");

		Projection name = Projections.property("name");
		Projection location = Projections.property("location");
		Projection id = Projections.property("id");
		// Projection userId = Projections.property("u.userId");

		ProjectionList projectionList = Projections.projectionList();

		projectionList.add(name, "name");
		projectionList.add(location, "location");
		projectionList.add(id, "id");
		// projectionList.add(userId);

		criteria.setProjection(projectionList);
		criteria.setResultTransformer(Transformers.aliasToBean(Theater.class));
		// criteria.setResultTransformer(Transformers.aliasToBean(User.class));
		return criteria.list();
	}

	@Override
	public List<Theater> getTheaters(Integer userPkId, Integer locationId,
			String partialName) {
		Criteria criteria = getCurrentSession().createCriteria(Theater.class);

		criteria.add(Restrictions.eq("user.id", userPkId));
		criteria.add(Restrictions.eq("location.id", locationId));
		criteria.add(Restrictions.like("name", partialName, MatchMode.ANYWHERE));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id"), "id");
		projectionList.add(Projections.property("name"), "name");
		projectionList.add(Projections.property("location"), "location");

		criteria.setProjection(projectionList);
		criteria.setResultTransformer(Transformers.aliasToBean(Theater.class));

		return criteria.list();
	}
}
