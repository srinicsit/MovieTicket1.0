package com.avihs.movie.web.book_ticket.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;
import com.avihs.movie.business.booking_ticket.service.BookingTicketService;
import com.avihs.movie.business.model.BookingStatus;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.movie_screen.service.MovieScreenService;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.screen.service.ScreenService;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats_status.model.SeatsStatus;
import com.avihs.movie.business.seats_status.service.SeatsStatusService;
import com.avihs.movie.business.transaction.model.Transaction;
import com.avihs.movie.business.user.model.User;
import com.avihs.movie.web.assign_movie.form.MovieScreenStatus;
import com.avihs.movie.web.assign_movie.form.MovieScreenStatus.TimeAndStatusInfo;
import com.avihs.movie.web.book_ticket.form.BookTicketForm;
import com.avihs.movie.web.util.Constants;
import com.avihs.movie.web.util.DataTableObject;

@Controller
@RequestMapping("/bookTicket")
public class BookTicketController {

	private static SimpleDateFormat dtWithWeekdayFormater = new SimpleDateFormat(
			"EEEE, d MMM");
	private static SimpleDateFormat threeLetterWeekFormater = new SimpleDateFormat(
			"EEE ");
	private static SimpleDateFormat dayFormater = new SimpleDateFormat("dd");

	private static final String BOOK_TICKET_PAGE = "book_ticket";

	@Autowired
	private MovieScreenService movieScreenService;

	@Autowired
	private ScreenService screenService;

	@Autowired
	private SeatsStatusService seatsStatusService;

	@Autowired
	BookingTicketService bookingTicketService;

	ObjectMapper mapper = new ObjectMapper();

	private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

	@RequestMapping(method = RequestMethod.GET)
	public String load(Model model) {
		BookTicketForm bookTicketForm = new BookTicketForm();
		model.addAttribute("bookTicketForm", bookTicketForm);
		return BOOK_TICKET_PAGE;
	}

	@RequestMapping(value = "/days", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> getDays() {
		Calendar calendar = new GregorianCalendar();

		List<Map<String, Object>> daysNameList = new ArrayList<Map<String, Object>>(
				5);

		for (int i = 0; i < 6; i++) {

			Map<String, Object> daysMap = new HashMap<String, Object>();
			Date date = calendar.getTime();
			daysMap.put("day", dayFormater.format(date));
			daysMap.put("name",
					i == 0 ? "Today" : threeLetterWeekFormater.format(date));
			daysMap.put("weekAndDay", dtWithWeekdayFormater.format(date));
			daysMap.put("date", date);

			daysNameList.add(daysMap);
			calendar.add(Calendar.DAY_OF_MONTH, 1);

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
				return o1.getShowDate().compareTo(o2.getShowDate());
			}
		});

		Map<Screen, MovieScreenStatus> movieScreenStatussMap = new HashMap<Screen, MovieScreenStatus>();
		MovieScreenStatus movieScreenStatus = null;
		for (MovieScreen movieScreen : movieScreens) {

			Map<Integer, Float> seatsClasCostMap = new HashMap<Integer, Float>();

			for (SeatClassType seatClassTypes : movieScreen.getScreen()
					.getSeatClassTypes()) {
				seatsClasCostMap.put(seatClassTypes.getSeatType().getId(),
						seatClassTypes.getTicketCost());
			}

			for (MovieScreenPrice movieScreenPrice : movieScreen
					.getMovieScreenPrices()) {

				seatsClasCostMap.put(movieScreenPrice.getSeatType().getId(),
						movieScreenPrice.getTicketCost());

			}

			List<SeatsStatusCount> seatsStatusCounts = seatsStatusService
					.getSeatsStatusCount(movieScreen.getId(),
							SeatStatus.AVAILABLE);
			for (SeatsStatusCount seatsStatusCount : seatsStatusCounts) {
				seatsStatusCount.setCost(seatsClasCostMap.get(seatsStatusCount
						.getClassTypeId()));
			}

			movieScreenStatus = movieScreenStatussMap.get(movieScreen
					.getScreen());
			if (movieScreenStatus == null) {
				movieScreenStatus = new MovieScreenStatus();
			}
			movieScreenStatus.setScreen(movieScreen.getScreen());
			TimeAndStatusInfo timeAndStatusInfo = new TimeAndStatusInfo();

			timeAndStatusInfo.setTime(timeFormat.format(movieScreen
					.getShowDate()));
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

	@RequestMapping(value = "/screenSeats/{movieScreenId}", method = RequestMethod.GET)
	public @ResponseBody
	List<SeatsStatus> getScreenSeats(
			@PathVariable("movieScreenId") Integer movieScreenId) {

		List<SeatsStatus> seatsStatusList = seatsStatusService
				.getSeatsStatus(movieScreenId);

		return seatsStatusList;
	}

	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	// public @ResponseBody
	// JsonResponse save(@Valid BookTicketForm bookTicketForm,
	// BindingResult bindingResult, Model model, HttpSession session) {
	// JsonResponse response = new JsonResponse();
	//
	// if (!bindingResult.hasErrors()) {
	// response.setStatus("SUCCESS");
	// List<SeatsStatus> seatsStatus = getJavaObject(bookTicketForm
	// .getBookingSeats());
	// for (SeatsStatus seat : seatsStatus) {
	// BookingTicket bookingTicket = new BookingTicket();
	// bookingTicket.setBookingStatus(BookingStatus.PAYMENT_WAITING);
	// seat.setSeatStatus(SeatStatus.NOT_AVAILABLE);
	// bookingTicket.setSeatStatus(seat);
	// User user = (User) session
	// .getAttribute(Constants.LOGGED_IN_USER);
	// bookingTicket.setUser(user);
	//
	// seatsStatusService.update(seat);
	// bookingTicketService.save(bookingTicket);
	//
	// }
	// } else {
	// response.setStatus("FAIL");
	// response.setResult(bindingResult.getAllErrors());
	// }
	// return response;
	// }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid BookTicketForm bookTicketForm,
			BindingResult bindingResult, Model model, HttpSession session) {

		if (!bindingResult.hasErrors()) {
			Transaction transaction = new Transaction();
			List<SeatsStatus> seatsStatus = getJavaObject(bookTicketForm
					.getBookingSeats());

			for (SeatsStatus seat : seatsStatus) {
				seat = seatsStatusService.loadSeatsStatus(seat.getId());
				BookingTicket bookingTicket = new BookingTicket();
				bookingTicket.setBookingStatus(BookingStatus.PAYMENT_WAITING);
				bookingTicket.setTransaction(transaction);
				seat.setSeatStatus(SeatStatus.NOT_AVAILABLE);
				bookingTicket.setSeatStatus(seat);
				User user = (User) session
						.getAttribute(Constants.LOGGED_IN_USER);
				bookingTicket.setUser(user);

				seatsStatusService.update(seat);
				bookingTicketService.save(bookingTicket);

			}
			return Constants.REDIRECT + "/payment?transactionId="
					+ transaction.getId();
		} else {
			return Constants.REDIRECT + "/payment";
		}

	}

	private List<SeatsStatus> getJavaObject(String jsonInput) {
		List<SeatsStatus> result = new ArrayList<SeatsStatus>();
		try {
			// we'll be reading instances of MyBean
			ObjectReader reader = mapper.reader(BookTicketForm.class);
			// and then do other configuration, if any, and read:
			BookTicketForm bookTicketForm = reader.readValue(jsonInput);
			result = bookTicketForm.getSeatsStatus();
			System.out.println("json result = " + result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
