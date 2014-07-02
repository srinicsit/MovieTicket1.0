package com.avihs.movie.web.movie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.movie.model.Movie;
import com.avihs.movie.business.movie.service.MovieService;
import com.avihs.movie.web.movie.form.MovieForm;
import com.avihs.movie.web.util.DataTableObject;
import com.avihs.movie.web.util.JsonResponse;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	public static final String MOVIES_PAGE = "movie";

	public static final String MOVIES_FORM = "movieForm";

	@RequestMapping(method = RequestMethod.GET)
	public String get(ModelMap model) {

		MovieForm movieForm = new MovieForm();
		model.addAttribute(MOVIES_FORM, movieForm);
		return MOVIES_PAGE;
	}

	@RequestMapping(value = "/listForDt", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<Movie> getAllMovies() {
		List<Movie> aaData = movieService.getAllMovies();
		DataTableObject<Movie> dataTableObject = new DataTableObject<Movie>();
		dataTableObject.setAaData(aaData);
		return dataTableObject;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Movie> listMovies() {
		List<Movie> movies = movieService.getAllMovies();
		return movies;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse add(@Valid MovieForm movieForm, ModelMap model,
			BindingResult bindingResult) {
		JsonResponse response = new JsonResponse();
		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			if (!movieService.isMovieExists(movieForm.getMovieName())) {
				Movie movie = new Movie();
				movie.setMovieName(movieForm.getMovieName());
				movie.setHours(movieForm.getHours());
				movie.setMins(movieForm.getMins());
				movie.setLanguage(movieForm.getLanguage());
				movieService.save(movie);
			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse update(@Valid MovieForm movieForm, ModelMap model,
			BindingResult bindingResult) {
		JsonResponse response = new JsonResponse();
		if (!bindingResult.hasErrors()) {
			if (!movieService.isMovieExists(movieForm.getMovieName(),
					movieForm.getMovieId())) {

				Movie movie = new Movie();
				movie.setId(movieForm.getMovieId());
				movie.setMovieName(movieForm.getMovieName());
				movie.setHours(movieForm.getHours());
				movie.setMins(movieForm.getMins());
				movie.setLanguage(movieForm.getLanguage());
				movieService.update(movie);

			}
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse delete(@Valid MovieForm movieForm, ModelMap model,
			BindingResult bindingResult) {
		JsonResponse response = new JsonResponse();
		if (!bindingResult.hasErrors()) {
			Movie movie = new Movie();
			movie.setMovieName(movieForm.getMovieName());
			movie.setId(movieForm.getMovieId());
			movieService.delete(movie);
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}
		return response;
	}

}
