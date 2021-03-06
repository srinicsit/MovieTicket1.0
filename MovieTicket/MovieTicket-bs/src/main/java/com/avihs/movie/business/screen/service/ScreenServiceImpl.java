package com.avihs.movie.business.screen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.screen.dao.ScreenDao;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenDao screenDao;

	@Override
	@Transactional
	public void save(Screen screen) {
		screenDao.save(screen);
	}

	@Override
	@Transactional
	public void update(Screen screen) {
		screenDao.update(screen);
	}

	@Override
	@Transactional
	public void delete(Screen screen) {
		screenDao.delete(screen);
	}

	public List<Screen> getScreens(Integer theaterId) {
		return screenDao.getScreens(theaterId);
	}

	@Transactional
	public boolean isScreenExists(Integer theaterId, String screenName) {
		return screenDao.isScreenExists(theaterId, screenName);
	}

	@Override
	public List<SeatClassType> getClassTypes(Integer screenId) {
		return screenDao.getClassTypes(screenId);
	}

	@Transactional
	@Override
	public Screen loadScreen(Integer screenId) {

		return screenDao.load(Screen.class, screenId);
	}

	@Override
	public List<Seats> getScreenSeats(Integer screenId) {
		return screenDao.getScreenSeats(screenId);
	}
}
