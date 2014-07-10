package com.avihs.movie.web.book_ticket.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.movie_screen.service.MovieScreenService;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.screen.service.ScreenService;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.business.seats_status.model.SeatsStatus;
import com.avihs.movie.business.seats_status.service.SeatsStatusService;
import com.avihs.movie.web.assign_movie.form.MovieScreenStatus;
import com.avihs.movie.web.assign_movie.form.MovieScreenStatus.TimeAndStatusInfo;
import com.avihs.movie.web.book_ticket.form.BookTicketForm;
import com.avihs.movie.web.util.DataTableObject;

@Controller
@RequestMapping("/bookTicket")
public class BookTicketController {

	private static final String BOOK_TICKET_PAGE = "book_ticket";

	@Autowired
	private MovieScreenService movieScreenService;

	@Autowired
	private ScreenService screenService;

	@Autowired
	private SeatsStatusService seatsStatusService;

	String[] weekDays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu",
			"Fri", "Sat" };

	@RequestMapping(method = RequestMethod.GET)
	public String load(Model model) {
		BookTicketForm bookTicketForm = new BookTicketForm();
		model.addAttribute("bookTicketForm", bookTicketForm);
		return BOOK_TICKET_PAGE;
	}

	@RequestMapping(value = "/days", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, String>> getDays() {

		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		int maxDays = today + 5;
		List<Map<String, String>> daysNameList = new ArrayList<Map<String, String>>(
				5);
		boolean fstTime = true;
		for (; today <= maxDays; today++) {
			Map<String, String> daysMap = new HashMap<String, String>();
			daysMap.put("day", (today < 10) ? ("0" + today) : today + "");
			daysMap.put("name", fstTime ? "Today" : weekDays[dayOfWeek - 1]);
			daysNameList.add(daysMap);
			dayOfWeek++;
			fstTime = false;
		}

		return daysNameList;
	}

	public @ResponseBody
	List<MovieScreen> getMovieTimingsForDay() {

		List<MovieScreen> movieScreens = new ArrayList<MovieScreen>();

		return movieScreens;
	}

	@RequestMapping(value = "/showList/{movieId}/{showDate}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<MovieScreenStatus> getMovieScreens(
			@PathVariable("movieId") Integer movieId,
			@PathVariable("showDate") Date showDate) {
		DataTableObject<MovieScreenStatus> dataTableObject = new DataTableObject<MovieScreenStatus>();

		List<MovieScreen> movieScreens = movieScreenService.getMovieScreens(
				movieId, showDate);
		Collections.sort(movieScreens, new Comparator<MovieScreen>() {

			@Override
			public int compare(MovieScreen o1, MovieScreen o2) {
				return o1.getShowHours().compareTo(o2.getShowHours());
			}
		});

		Map<Screen, MovieScreenStatus> movieScreenStatussMap = new HashMap<Screen, MovieScreenStatus>();
		MovieScreenStatus movieScreenStatus = null;
		for (MovieScreen movieScreen : movieScreens) {
			List<SeatsStatusCount> seatsStatusCounts = seatsStatusService
					.getSeatsStatusCount(movieScreen.getId(),
							SeatStatus.AVAILABLE);
			movieScreenStatus = movieScreenStatussMap.get(movieScreen
					.getScreen());
			if (movieScreenStatus == null) {
				movieScreenStatus = new MovieScreenStatus();
			}
			movieScreenStatus.setScreen(movieScreen.getScreen());
			TimeAndStatusInfo timeAndStatusInfo = new TimeAndStatusInfo();
			if (movieScreen.getShowHours() - 12 < 0) {
				timeAndStatusInfo.setTime(movieScreen.getShowHours() + ":"
						+ movieScreen.getShowMins() + " AM");

			} else {
				timeAndStatusInfo.setTime((movieScreen.getShowHours() - 12)
						+ ":" + movieScreen.getShowMins() + " PM");

			}
			timeAndStatusInfo.setSeatsStatusCounts(seatsStatusCounts);
			timeAndStatusInfo.setMovieScreenId(movieScreen.getId());
			movieScreenStatussMap.put(movieScreen.getScreen(),
					movieScreenStatus);
			movieScreenStatus.getTimingsAndAvailableInfo().add(
					timeAndStatusInfo);
		}

		List<MovieScreenStatus> moviScreenTimingsList = new ArrayList<MovieScreenStatus>(
				0);
		moviScreenTimingsList.addAll(movieScreenStatussMap.values());
		dataTableObject.setAaData(moviScreenTimingsList);

		return dataTableObject;
	}

	@RequestMapping(value = "/seats/{movieScreenId}", method = RequestMethod.GET)
	public @ResponseBody
	List<SeatsStatus> getSeats(
			@PathVariable("movieScreenId") Integer movieScreenId) {
		List<SeatsStatus> seatsStatusList = null;

		return seatsStatusList;
	}

	@RequestMapping(value = "/screenSeats/{movieScreenId}", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getScreenSeats(
			@PathVariable("movieScreenId") Integer movieScreenId) {

		Map<String, Object> seatsMap = new HashMap<String, Object>();
		MovieScreen movieScreen = movieScreenService
				.loadMovieScreen(movieScreenId);
		List<SeatClassType> seatClassTypes = screenService
				.getClassTypes(movieScreen.getScreen().getId());
		seatsMap.put("seatClassTypes", seatClassTypes);

		List<SeatsStatus> seatsStatusList = seatsStatusService
				.getSeatsStatus(movieScreenId);
		seatsMap.put("seatsStatusList", seatsStatusList);

		return seatsMap;
	}
}
