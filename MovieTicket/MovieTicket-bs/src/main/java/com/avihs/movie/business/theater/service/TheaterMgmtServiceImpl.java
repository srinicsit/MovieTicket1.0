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
	private TheaterMgmtDao<Theater> theaterMgmtDao;

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
	public boolean isTheaterExists(String theaterName, String location) {
		return theaterMgmtDao.isTheaterExists(theaterName, location);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isTheaterExists(Integer pkId, String theaterName,
			String location) {
		return theaterMgmtDao.isTheaterExists(pkId, theaterName, location);
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
	public List<Theater> getTheaters(String location) {

		return theaterMgmtDao.getTheaters(location);
	}

	@Override
	public List<Theater> getTheaters(Integer userPkId) {
		return theaterMgmtDao.getTheaters(userPkId);
	}

	@Override
	public List<Theater> getTheaters(Integer userPkId, String location,
			String partialName) {
		return theaterMgmtDao.getTheaters(userPkId, location, partialName);
	}

}
