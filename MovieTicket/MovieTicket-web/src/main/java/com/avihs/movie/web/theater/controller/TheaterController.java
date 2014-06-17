package com.avihs.movie.web.theater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.theater.service.TheaterMgmtService;
import com.avihs.movie.web.theater.form.TheaterForm;
import javax.validation.Valid;

@Controller
@RequestMapping("/theater")
public class TheaterController {

	private static final String THEATER_PAGE = "theater";
	@Autowired
	private TheaterMgmtService theaterMgmtService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		model.addAttribute("theaterForm", new TheaterForm());
		return THEATER_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid TheaterForm theaterForm,
			BindingResult bindingResult, Model model) {

		Theater theater = new Theater();
		theater.setName(theaterForm.getName());
		theater.setLocation(theaterForm.getLocation());
		if (!theaterMgmtService.isTheaterExists(theaterForm.getName(),
				theaterForm.getLocation())) {
			theaterMgmtService.create(theater);
		}
		return THEATER_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST, params = "update")
	public String update(TheaterForm theaterForm) {

		Theater theater = new Theater();
		theater.setName(theaterForm.getName());
		theater.setLocation(theaterForm.getLocation());
		theaterMgmtService.create(theater);
		return THEATER_PAGE;
	}
}
