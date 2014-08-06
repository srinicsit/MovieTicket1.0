package com.avihs.movie.business.seat_class_type.dao;

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

@Repository
public class SeatClassTypeDaoImpl extends CommonDaoImpl implements
		SeatClassTypeDao {

	@Override
	public void deleteSeatClasses(Integer screenId) {
		Query query = getCurrentSession().getNamedQuery(
				"delSeatClassTypesForScreen");
		query.setInteger("screen_id", screenId);
		int updatedRows = query.executeUpdate();
		System.out.println("updated rows = " + updatedRows);

	}

	public List<SeatClassType> getClassTypesForScreen(Integer screenId) {

		Criteria cr = getCurrentSession()
				.createCriteria(SeatClassType.class)
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.property("id"), "id")
								.add(Projections.property("seatType"),
										"seatType"))

				.setResultTransformer(
						Transformers.aliasToBean(SeatClassType.class));

		Screen screen = new Screen();
		screen.setId(screenId);
		cr.add(Restrictions.eq("screen", screen));

		List<SeatClassType> list = cr.list();
		return list;

	}
}
