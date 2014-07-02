package com.avihs.movie.business.theater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.theater.dao.TheaterMgmtDao;
import com.avihs.movie.business.theater.model.Theater;

@Service
public class TheaterMgmtServiceImpl implements TheaterMgmtService {

	@Autowired
	private TheaterMgmtDao theaterMgmtDao;

	public TheaterMgmtServiceImpl() {
		System.out.println("TheaterMgmtServiceImpl");
	}

	public void save(Theater theater) {
		theaterMgmtDao.save(theater);
	}

	public Theater getTheater(Integer id) {

		return theaterMgmtDao.get(Theater.class, id);
	}

	@Override
	@Transactional
	public void create(Theater theater) {
		theaterMgmtDao.save(theater);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isTheaterExists(String theaterName, Integer locationId) {
		return theaterMgmtDao.isTheaterExists(theaterName, locationId);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isTheaterExists(Integer theaterId, String theaterName,
			Integer locationId) {
		return theaterMgmtDao.isTheaterExists(theaterId, theaterName,
				locationId);
	}

	@Transactional
	public void update(Theater theater) {
		theaterMgmtDao.update(theater);
	}

	@Transactional
	public void delete(Theater theater) {
		theaterMgmtDao.delete(theater);
	}

	@Override
	public List<Theater> getTheaters(Integer locationId) {

		return theaterMgmtDao.getTheaters(locationId);
	}

	@Override
	public List<Theater> getTheatersForUser(Integer userPkId) {
		return theaterMgmtDao.getTheatersForUser(userPkId);
	}

	@Override
	public List<Theater> getTheaters(Integer userPkId, Integer locationId,
			String partialName) {
		return theaterMgmtDao.getTheaters(userPkId, locationId, partialName);
	}

}
