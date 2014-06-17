package com.avihs.movie.web.controller.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avihs.movie.business.theater.service.TheaterMgmtService;

@Controller
@RequestMapping("/demo")
public class DemorController {

	@Autowired
	private TheaterMgmtService theaterMgmtService;

	public DemorController() {
		System.out.println("TheaterController");
	}

	@RequestMapping( method = RequestMethod.GET)
	public String getMovie() {

//		model.addAttribute("movie", name);
		return "list";

	}

}
