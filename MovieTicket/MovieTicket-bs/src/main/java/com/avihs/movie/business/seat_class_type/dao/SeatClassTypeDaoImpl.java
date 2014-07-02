package com.avihs.movie.business.seat_class_type.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;

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

}
