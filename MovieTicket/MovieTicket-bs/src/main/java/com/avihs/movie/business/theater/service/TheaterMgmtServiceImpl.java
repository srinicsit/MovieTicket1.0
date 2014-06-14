package com.avihs.movie.business.theater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
