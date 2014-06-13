package com.avihs.movie.web.controller.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avihs.movie.web.controller.service.TestService;

@Controller
@RequestMapping("/greeting")
public class TheaterController {

	@Autowired
	private TestService testService;


	// @Autowired
	// private TheaterMgmtService theaterMgmtService;

	public TheaterController() {
		System.out.println("TheaterController");
	}

	@RequestMapping(method = RequestMethod.GET)
	public void load() {
		System.out.println("load");
	}

}
