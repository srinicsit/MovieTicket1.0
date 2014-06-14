package com.avihs.movie.business.theater.dao;

import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.theater.model.Theater;

@Repository
public class TheaterMgmtDaoImpl extends CommonDaoImpl<Theater> implements
		TheaterMgmtDao<Theater> {

	public TheaterMgmtDaoImpl() {
		System.out.println("TheaterMgmtDaoImpl");
	}

}
