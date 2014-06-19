package com.avihs.movie.web.screen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avihs.movie.business.screen.service.ScreenService;
import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.theater.service.TheaterMgmtService;
import com.avihs.movie.web.screen.form.ScreenForm;

@Controller
@RequestMapping("/screen")
public class ScreenController {
	private static final String SCREEN_PAGE = "screen";

	@Autowired
	private TheaterMgmtService theaterMgmtService;

	@Autowired
	ScreenService screenService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		ScreenForm screenForm = new ScreenForm();

		List<Theater> theaters = theaterMgmtService.getTheaters(screenForm
				.getLocation());

		model.addAttribute("screenForm", screenForm);
		model.addAttribute("theaters", theaters);

		return SCREEN_PAGE;
	}

	@RequestMapping(method = RequestMethod.GET, params = "loadTheaters")
	public String loadTheaters(@Valid ScreenForm screenForm, ModelMap model) {

		List<Theater> theaters = theaterMgmtService.getTheaters(screenForm
				.getLocation());

		model.addAttribute("screenForm", screenForm);
		model.addAttribute("theaters", theaters);

		return SCREEN_PAGE;
	}

	public String submit(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model) {

		return SCREEN_PAGE;
	}
}
