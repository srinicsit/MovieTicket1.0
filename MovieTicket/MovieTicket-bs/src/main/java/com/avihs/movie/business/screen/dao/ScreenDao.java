package com.avihs.movie.business.screen.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;

public interface ScreenDao extends CommonDao {

	public List<Screen> getScreens(Integer theaterId);

	public boolean isScreenExists(Integer theaterId, String screenName);
	
	public List<SeatClassType> getClassTypes(Integer screenId);
}
