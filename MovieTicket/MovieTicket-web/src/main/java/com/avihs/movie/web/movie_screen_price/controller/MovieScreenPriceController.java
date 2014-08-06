package com.avihs.movie.web.movie_screen_price.controller;

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

import com.avihs.movie.business.model.SeatTypes;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;
import com.avihs.movie.business.movie_screen_price.service.MovieScreenPriceService;
import com.avihs.movie.web.movie_screen_price.form.MovieScreenPriceForm;
import com.avihs.movie.web.util.DataTableObject;
import com.avihs.movie.web.util.JsonResponse;

@Controller
@RequestMapping("/movieScreenPrice")
public class MovieScreenPriceController {

	public static final String MOVIE_SCREEN_PRICE_PAGE = "movie_screen_price";

	@Autowired
	private MovieScreenPriceService movieScreenPriceService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(ModelMap model) {
		MovieScreenPriceForm movieScreenPriceForm = new MovieScreenPriceForm();
		model.addAttribute("movieScreenPriceForm", movieScreenPriceForm);
		return MOVIE_SCREEN_PRICE_PAGE;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse save(@Valid MovieScreenPriceForm movieScreenPriceForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			MovieScreenPrice movieScreenPrice = new MovieScreenPrice();

			MovieScreen movieScreen = new MovieScreen();
			movieScreen.setId(movieScreenPriceForm.getMovieScreenId());

			SeatTypes seatType = new SeatTypes();
			seatType.setId(movieScreenPriceForm.getSeatClsName());

			movieScreenPrice.setTicketCost(movieScreenPriceForm.getPrice());
			movieScreenPrice.setSeatType(seatType);
			movieScreenPrice.setMovieScreen(movieScreen);
			movieScreenPriceService.save(movieScreenPrice);
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}

		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse update(@Valid MovieScreenPriceForm movieScreenPriceForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");

			MovieScreenPrice movieScreenPrice = movieScreenPriceService
					.getMovieScreenPrice(movieScreenPriceForm
							.getMovieScreenPriceId());

			MovieScreen movieScreen = new MovieScreen();
			movieScreen.setId(movieScreenPriceForm.getMovieScreenId());

			SeatTypes seatType = new SeatTypes();
			seatType.setId(movieScreenPriceForm.getSeatClsName());

			movieScreenPrice.setTicketCost(movieScreenPriceForm.getPrice());
			movieScreenPrice.setSeatType(seatType);
			movieScreenPrice.setMovieScreen(movieScreen);
			movieScreenPriceService.update(movieScreenPrice);

		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}

		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse delete(@Valid MovieScreenPriceForm movieScreenPriceForm,
			BindingResult bindingResult, ModelMap model) {
		JsonResponse response = new JsonResponse();

		if (!bindingResult.hasErrors()) {
			response.setStatus("SUCCESS");
			MovieScreenPrice movieScreenPrice = movieScreenPriceService
					.loadMovieScreenPrice(movieScreenPriceForm
							.getMovieScreenPriceId());
			movieScreenPriceService.delete(movieScreenPrice);
		} else {
			response.setStatus("FAIL");
			response.setResult(bindingResult.getAllErrors());
		}

		return response;
	}

	@RequestMapping(value = "/{movieScreenId}", method = RequestMethod.GET)
	public @ResponseBody
	DataTableObject<MovieScreenPrice> getMovieScreenPrices(
			@PathVariable("movieScreenId") Integer movieScreenId) {
		DataTableObject<MovieScreenPrice> dataTableObject = new DataTableObject<MovieScreenPrice>();
		List<MovieScreenPrice> movieScreenPrices = movieScreenPriceService
				.getMovieScreenPrices(movieScreenId);
		dataTableObject.setAaData(movieScreenPrices);

		return dataTableObject;
	}

}
