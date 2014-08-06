package com.avihs.movie.business.seat_class_type.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;

public interface SeatClassTypeDao extends CommonDao {

	public void deleteSeatClasses(Integer screenId);
	
	public List<SeatClassType> getClassTypesForScreen(Integer screenId);
}
