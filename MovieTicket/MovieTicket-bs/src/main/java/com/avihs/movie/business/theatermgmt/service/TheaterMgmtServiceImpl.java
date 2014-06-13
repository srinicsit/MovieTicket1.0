package com.avihs.movie.business.theatermgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.theatermgmt.dao.TheaterMgmtDao;

@Service
public class TheaterMgmtServiceImpl implements TheaterMgmtService {

	public TheaterMgmtServiceImpl() {
		System.out.println("TheaterMgmtServiceImpl");
	}

}
