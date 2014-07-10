package com.avihs.movie.business.screen.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.business.theater.model.Theater;

@Repository
public class ScreenDaoImpl extends CommonDaoImpl implements ScreenDao {

	public List<Screen> getScreens(Integer theaterId) {

		Criteria cr = getCurrentSession()
				.createCriteria(Screen.class)
				.setProjection(
						Projections.projectionList()
								.add(Projections.property("id"), "id")
								.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Screen.class));

		Theater theater = new Theater();
		theater.setId(theaterId);
		cr.add(Restrictions.eq("theater", theater));

		List<Screen> list = cr.list();
		return list;
	}

	public boolean isScreenExists(Integer theaterId, String screenName) {

		Query query = getCurrentSession().getNamedQuery("isScreenExists");
		query.setInteger("theaterId", theaterId);
		query.setString("name", screenName);
		return query.list().size() > 0;
	}

	public List<SeatClassType> getClassTypes(Integer screenId) {
		List<SeatClassType> seatClassTypes = null;

		Query query = getCurrentSession().getNamedQuery(
				"getSeatClassTypesForScreen");
		query.setInteger("screen_id", screenId);

		seatClassTypes = query.list();

		return seatClassTypes;

	}

	public List<Seats> getScreenSeats(Integer screenId) {

		Query query = getCurrentSession().getNamedQuery("getScreenSeats");
		query.setInteger("screenId", screenId);
		return query.list();
	}

}
