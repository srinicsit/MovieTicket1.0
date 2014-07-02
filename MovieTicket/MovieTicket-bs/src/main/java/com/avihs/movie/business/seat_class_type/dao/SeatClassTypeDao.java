package com.avihs.movie.business.seat_class_type.dao;

import com.avihs.movie.business.dao.CommonDao;

public interface SeatClassTypeDao extends CommonDao {

	public void deleteSeatClasses(Integer screenId);
}
