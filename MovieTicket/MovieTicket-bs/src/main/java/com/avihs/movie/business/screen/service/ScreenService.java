package com.avihs.movie.business.screen.service;

import java.util.List;

import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;

public interface ScreenService {
	public void save(Screen screen);

	public void update(Screen screen);

	public List<Screen> getScreens(Integer theaterId);

	public Screen loadScreen(Integer screenId);

	public void delete(Screen screen);

	public boolean isScreenExists(Integer theaterId, String screenName);

	public List<SeatClassType> getClassTypes(Integer screenId);
	
	public List<Seats> getScreenSeats(Integer screenId);


}
