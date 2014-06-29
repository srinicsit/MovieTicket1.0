package com.avihs.movie.business.screen.service;

import java.util.List;

import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;

public interface ScreenService {
	public void save(Screen screen);

	public void update(Screen screen);

	public List<Screen> getScreens(Integer theaterId);
	
	public void delete(Screen screen);
	
	public boolean isScreenExists(Integer theaterId, String screenName);
	
	public List<SeatClassType> getClassTypes(Integer screenId);
}
