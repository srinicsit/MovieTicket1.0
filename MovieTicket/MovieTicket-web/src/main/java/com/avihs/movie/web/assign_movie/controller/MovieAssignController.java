package com.avihs.movie.web.assign_movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.movie.model.Movie;
import com.avihs.movie.business.movie.service.MovieService;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.movie_screen.service.MovieScreenService;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.screen.service.ScreenService;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.business.seats_status.model.SeatsStatus;
import com.avihs.movie.business.seats_status.service.SeatsStatusService;
import com.avihs.movie.web.assign_movie.form.MovieAssignForm;
import com.avihs.movie.web.util.DataTableObject;
import com.avihs.movie.web.util.JsonResponse;

@Controller
@RequestMapping("/movieAssign")
public class MovieAssignController {

	public static final String MOVIE_ASSIGN_PAGE = "assign_movie";

	@Autowired
	private MovieScreenService movieScreenService;

	@Autowired
	private MovieService movieService;

	@Autowired
	ScreenService screenService;

	@Autowired
	private SeatsStatusService seatsStatusService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		MovieAssignForm movieAssignForm = new MovieAssignForm();
		List<Movie> allMovies = movieService.getAllMovies();

		model.addAttribute("movieAssignForm", movieAssignForm);

		model.addAttribute("allMovies", allMovies);

		return MOVIE_ASSIGN_PAGE;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse save(@Valid MovieAssignForm movieAssignForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			MovieScreen movieScreen = new MovieScreen();

			Movie movie = new Movie();
			movie.setId(movieAssignForm.getMovieId());

			Screen screen = screenService.loadScreen(movieAssignForm
					.getScreenId());

			movieScreen.setMovie(movie);
			movieScreen.setScreen(screen);

			movieScreen.setShowDate(movieAssignForm.getShowDate());
			movieScreen.setShowHours(movieAssignForm.getHours());
			movieScreen.setShowMins(movieAssignForm.getMins());

			movieScreenService.save(movieScreen);
			assignSeatsToMovieScreen(movieScreen, screen);
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}

		return response;
	}

	private void assignSeatsToMovieScreen(MovieScreen movieScreen, Screen screen) {

		List<Seats> seats = screenService.getScreenSeats(screen.getId());

		List<SeatsStatus> seatsStatusList = new ArrayList<SeatsStatus>();
		for (Seats seat : seats) {
			SeatsStatus seatsStatus = new SeatsStatus();
			seatsStatus.setSeatStatus(SeatStatus.AVAILABLE);
			seatsStatus.setMovieScreen(movieScreen);
			seatsStatus.setSeat(seat);
			seatsStatusList.add(seatsStatus);
		}
		seatsStatusService.save(seatsStatusList);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse update(@Valid MovieAssignForm movieAssignForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			if (movieAssignForm.getMovieAssignId() != null) {
				MovieScreen movieScreen = new MovieScreen();
				movieScreen.setId(movieAssignForm.getMovieAssignId());

				Movie movie = new Movie();
				movie.setId(movieAssignForm.getMovieId());

				Screen screen = new Screen();
				screen.setId(movieAssignForm.getScreenId());

				movieScreen.setMovie(movie);
				movieScreen.setScreen(screen);

				movieScreen.setShowDate(movieAssignForm.getShowDate());
				movieScreen.setShowHours(movieAssignForm.getHours());
				movieScreen.setShowMins(movieAssignForm.getMins());

				movieScreenService.update(movieScreen);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse delete(@Valid MovieAssignForm movieAssignForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			MovieScreen movieScreen = movieScreenService
					.loadMovieScreen(movieAssignForm.getMovieAssignId());
			if (movieScreen != null) {
				movieScreenService.delete(movieScreen);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/listForDt/{movieId}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<MovieScreen> getMovieScreens(
			@PathVariable("movieId") Integer movieId) {
		DataTableObject<MovieScreen> dataTableObject = new DataTableObject<MovieScreen>();

		List<MovieScreen> movieScreens = movieScreenService
				.getMovieScreens(movieId);
		dataTableObject.setAaData(movieScreens);

		return dataTableObject;
	}


}
