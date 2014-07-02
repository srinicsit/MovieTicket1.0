package com.avihs.movie.web.theater.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.location.model.Location;
import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.theater.service.TheaterMgmtService;
import com.avihs.movie.business.user.model.User;
import com.avihs.movie.web.theater.form.TheaterForm;
import com.avihs.movie.web.util.Constants;
import com.avihs.movie.web.util.DataTableObject;
import com.avihs.movie.web.util.JsonResponse;

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
		return THEATER_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse submit(@Valid TheaterForm theaterForm,
			BindingResult bindingResult, Model model, HttpSession session) {

		JsonResponse response = new JsonResponse();
		// ValidationUtils.rejectIfEmpty(result, "name",
		// "Name can not be empty.");

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
			Theater theater = new Theater();
			theater.setName(theaterForm.getName());
			Location location = new Location();
			location.setId(theaterForm.getLocationId());
			theater.setLocation(location);
			theater.setUser(user);
			// theater.setModifiedUser(user);
			if (!theaterMgmtService.isTheaterExists(theaterForm.getName(),
					theaterForm.getLocationId())) {
				theaterMgmtService.create(theater);
			}

		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());

		}

		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse update(TheaterForm theaterForm, BindingResult bindingResult,
			HttpSession session) {
		JsonResponse response = new JsonResponse();
		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			Integer theaterId = theaterForm.getTheaterId();
			if (!theaterMgmtService.isTheaterExists(theaterId,
					theaterForm.getName(), theaterForm.getLocationId())) {

				Theater theater = theaterMgmtService.getTheater(theaterId);
				// theater.setId(theaterForm.getTheaterId());
				theater.setName(theaterForm.getName());
				Location location = new Location();
				location.setId(theaterForm.getLocationId());
				theater.setLocation(location);
				User user = (User) session
						.getAttribute(Constants.LOGGED_IN_USER);
				// theater.setModifiedUser(user);
				theaterMgmtService.update(theater);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse delete(TheaterForm theaterForm, BindingResult bindingResult,
			HttpSession session) {
		JsonResponse response = new JsonResponse();
		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			Theater theater = new Theater();
			theater.setId(theaterForm.getTheaterId());
			theaterMgmtService.delete(theater);
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/dtList/{location}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<Theater> dtListForLocation(
			@PathVariable("location") Integer locationId, HttpSession session) {
		List<Theater> theaters = theaterMgmtService.getTheaters(locationId);
		DataTableObject<Theater> dataTableObject = new DataTableObject<Theater>();
		dataTableObject.setAaData(theaters);
		return dataTableObject;
	}

	@RequestMapping(value = "/list/{location}", method = RequestMethod.GET)
	public @ResponseBody
	List<Theater> list(@PathVariable("location") Integer locationId,
			@RequestParam("term") String name, HttpSession session) {
		User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
		List<Theater> theaters = theaterMgmtService.getTheaters(user.getId(),
				locationId, name);

		return theaters;
	}

	@RequestMapping(value = "/fullList/{location}", method = RequestMethod.GET)
	public @ResponseBody
	List<Theater> list(@PathVariable("location") Integer locationId,
			HttpSession session) {
		List<Theater> theaters = theaterMgmtService.getTheaters(locationId);

		return theaters;
	}
}
