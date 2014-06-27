package com.avihs.movie.web.screen.controller;

import java.io.IOException;
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

		List<Theater> theaters = theaterMgmtService.getTheaters(screenForm
				.getLocation());

		model.addAttribute("screenForm", screenForm);
		model.addAttribute("theaters", theaters);

		return SCREEN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {
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

			User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
			screen.setModifiedUser(user);

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

			screenService.save(screen);
			ScreenForm newScreenForm = getNewScreenForm(screenForm);
			model.addAttribute("screenForm", newScreenForm);

		}
		return SCREEN_PAGE;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		if (screenForm.getScreenId() != null) {
			Screen screen = new Screen();
			screen.setName(screenForm.getName());
			// Theater theater = theaterMgmtService.getTheater(screenForm
			// .getTheaterId());
			Theater theater = new Theater();
			theater.setId(screenForm.getTheaterId());
			screen.setId(screenForm.getScreenId());
			screen.setTheater(theater);
			User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
			screen.setModifiedUser(user);

			ScreenForm newScreenForm = getNewScreenForm(screenForm);
			model.addAttribute("screenForm", newScreenForm);

			screenService.update(screen);
		}
		return SCREEN_PAGE;
	}

	private ScreenForm getNewScreenForm(ScreenForm screenForm) {
		ScreenForm newScreenForm = new ScreenForm();
		newScreenForm.setLocation(screenForm.getLocation());
		newScreenForm.setTheaterName(screenForm.getTheaterName());
		newScreenForm.setTheaterId(screenForm.getTheaterId());
		return newScreenForm;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@Valid ScreenForm screenForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		if (screenForm.getScreenId() != null) {
			Screen screen = new Screen();
			screen.setId(screenForm.getScreenId());
			screenService.delete(screen);

			ScreenForm newScreenForm = getNewScreenForm(screenForm);
			model.addAttribute("screenForm", newScreenForm);
		}
		return SCREEN_PAGE;
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
