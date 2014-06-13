package com.avihs.movie.web.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.theatermgmt.service.TheaterMgmtService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TheaterMgmtService theaterMgmtService;
}
