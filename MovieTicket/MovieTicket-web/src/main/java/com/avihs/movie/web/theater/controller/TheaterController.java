package com.avihs.movie.web.theater.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.theater.service.TheaterMgmtService;
import com.avihs.movie.business.user.model.User;
import com.avihs.movie.web.theater.form.TheaterForm;
import com.avihs.movie.web.util.Constants;
import com.avihs.movie.web.util.DataTableObject;

@Controller
@RequestMapping("/theater")
public class TheaterController {

	private static final String THEATER_PAGE = "theater";
	@Autowired
	private TheaterMgmtService theaterMgmtService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		TheaterForm theaterForm = new TheaterForm();
		model.addAttribute("theaterForm", theaterForm);
		List<Theater> theaters = theaterMgmtService.getTheaters(theaterForm
				.getLocation());
		model.addAttribute("theaters", theaters);
		return THEATER_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid TheaterForm theaterForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
		Theater theater = new Theater();
		theater.setName(theaterForm.getName());
		theater.setLocation(theaterForm.getLocation());
		theater.setUser(user);
		theater.setModifiedUser(user);
		if (!theaterMgmtService.isTheaterExists(theaterForm.getName(),
				theaterForm.getLocation())) {
			theaterMgmtService.create(theater);
		}
		return THEATER_PAGE;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TheaterForm theaterForm, HttpSession session) {
		Integer theaterId = theaterForm.getTheaterId();
		if (!theaterMgmtService.isTheaterExists(theaterId,
				theaterForm.getName(), theaterForm.getLocation())) {

			Theater theater = theaterMgmtService.getTheater(theaterId);
			// theater.setId(theaterForm.getTheaterId());
			theater.setName(theaterForm.getName());
			theater.setLocation(theaterForm.getLocation());
			User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
			theater.setModifiedUser(user);
			theaterMgmtService.update(theater);
		}
		return THEATER_PAGE;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(TheaterForm theaterForm, HttpSession session) {
		Theater theater = new Theater();
		theater.setId(theaterForm.getTheaterId());
		theaterMgmtService.delete(theater);
		return THEATER_PAGE;
	}

	@RequestMapping(value = "/list/{location}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<Theater> list(@PathVariable String location) {
		List<Theater> theaters = theaterMgmtService.getTheaters(location);
		DataTableObject<Theater> dataTableObject = new DataTableObject<Theater>();
		dataTableObject.setAaData(theaters);
		return dataTableObject;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<Theater> list(HttpSession session) {
		User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
		List<Theater> theaters = theaterMgmtService.getTheaters(user.getId());
		DataTableObject<Theater> dataTableObject = new DataTableObject<Theater>();
		dataTableObject.setAaData(theaters);
		return dataTableObject;
	}
}
