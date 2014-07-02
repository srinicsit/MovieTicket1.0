package com.avihs.movie.web.screen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.rows.model.Rows;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.screen.service.ScreenService;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seat_class_type.service.SeatClassTypeService;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.theater.service.TheaterMgmtService;
import com.avihs.movie.business.user.model.User;
import com.avihs.movie.web.screen.form.ScreenForm;
import com.avihs.movie.web.util.Constants;
import com.avihs.movie.web.util.DataTableObject;
import com.avihs.movie.web.util.JsonResponse;

@Controller
@RequestMapping("/screen")
public class ScreenController {
	private static final String SCREEN_PAGE = "screen";

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private TheaterMgmtService theaterMgmtService;

	@Autowired
	ScreenService screenService;

	@Autowired
	private SeatClassTypeService seatClassTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		ScreenForm screenForm = new ScreenForm();

		model.addAttribute("screenForm", screenForm);

		return SCREEN_PAGE;
	}

	@RequestMapping(method = RequestMethod.GET, params = "loadTheaters")
	public String loadTheaters(@Valid ScreenForm screenForm, ModelMap model) {
		model.addAttribute("screenForm", screenForm);
		return SCREEN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse save(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			if (screenForm.getTheaterId() != null
					&& !screenService.isScreenExists(screenForm.getTheaterId(),
							screenForm.getName())) {
				Screen screen = new Screen();
				screen.setName(screenForm.getName());
				screen.setRows(screenForm.getRows());
				screen.setCols(screenForm.getColumns());

				// Theater theater = theaterMgmtService.getTheater(screenForm
				// .getTheaterId());
				Theater theater = new Theater();
				theater.setId(screenForm.getTheaterId());
				screen.setTheater(theater);

				User user = (User) session
						.getAttribute(Constants.LOGGED_IN_USER);
				// screen.setModifiedUser(user);

				setSeatClassTypes(screenForm, screen);

				screenService.save(screen);
				ScreenForm newScreenForm = getNewScreenForm(screenForm);
				model.addAttribute("screenForm", newScreenForm);

			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	private void setSeatClassTypes(ScreenForm screenForm, Screen screen) {
		List<SeatClassType> seatClassTypes = getJavaObject(screenForm
				.getSeatsInfo());
		if (seatClassTypes != null) {
			for (SeatClassType seatClassType : seatClassTypes) {
				seatClassType.setScreen(screen);
				for (Rows row : seatClassType.getRowsList()) {
					row.setSeatClassType(seatClassType);
					for (Seats seat : row.getSeats()) {
						seat.setRow(row);
					}

				}
			}
			screen.getSeatClassTypes().addAll(seatClassTypes);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse update(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {

		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			if (screenForm.getScreenId() != null) {
				Screen screen = new Screen();
				screen.setName(screenForm.getName());
				// Theater theater = theaterMgmtService.getTheater(screenForm
				// .getTheaterId());
				Theater theater = new Theater();
				theater.setId(screenForm.getTheaterId());
				screen.setId(screenForm.getScreenId());
				screen.setTheater(theater);
				User user = (User) session
						.getAttribute(Constants.LOGGED_IN_USER);
				// screen.setModifiedUser(user);

				ScreenForm newScreenForm = getNewScreenForm(screenForm);
				model.addAttribute("screenForm", newScreenForm);
				setSeatClassTypes(screenForm, screen);
				screenService.update(screen);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	private ScreenForm getNewScreenForm(ScreenForm screenForm) {
		ScreenForm newScreenForm = new ScreenForm();
		newScreenForm.setTheaterId(screenForm.getTheaterId());
		return newScreenForm;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse delete(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			if (screenForm.getScreenId() != null) {
				Screen screen = new Screen();
				screen.setId(screenForm.getScreenId());
				//seatClassTypeService.deleteSeatClasses(screenForm.getScreenId());
				screenService.delete(screen);

				ScreenForm newScreenForm = getNewScreenForm(screenForm);
				model.addAttribute("screenForm", newScreenForm);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/{theaterId}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<Screen> getScreens(
			@PathVariable("theaterId") Integer theaterId) {

		DataTableObject<Screen> dataTableObject = new DataTableObject<Screen>();
		try {
			dataTableObject = new DataTableObject<Screen>();
			List<Screen> screens = screenService.getScreens(theaterId);
			dataTableObject.setAaData(screens);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataTableObject;

	}

	@RequestMapping(value = "/list/{theaterId}", method = RequestMethod.GET)
	public @ResponseBody
	List<Screen> getScreensList(@PathVariable("theaterId") Integer theaterId) {

		List<Screen> screens = new ArrayList<Screen>();
		try {
			screens = screenService.getScreens(theaterId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return screens;

	}

	@RequestMapping(value = "/clsTypes/{screenId}", method = RequestMethod.GET)
	public @ResponseBody
	List<SeatClassType> getClassTypes(@PathVariable("screenId") Integer screenId) {
		List<SeatClassType> seatClassTypes = screenService
				.getClassTypes(screenId);

		return seatClassTypes;
	}

	private List<SeatClassType> getJavaObject(String jsonInput) {
		Screen result = null;
		try {
			// we'll be reading instances of MyBean
			ObjectReader reader = mapper.reader(Screen.class);
			// and then do other configuration, if any, and read:
			result = reader.readValue(jsonInput);
			System.out.println("json result = " + result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result != null ? result.getSeatClassTypes() : null;
	}
}
