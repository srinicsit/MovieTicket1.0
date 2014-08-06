package com.avihs.movie.business.movie_screen_price.service;

import java.util.List;

import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;

public interface MovieScreenPriceService {

	public void save(MovieScreenPrice movieScreenPrice);

	public void update(MovieScreenPrice movieScreenPrice);

	public MovieScreenPrice getMovieScreenPrice(Integer movieScreenPriceId);
	
	public MovieScreenPrice loadMovieScreenPrice(Integer movieScreenPriceId);
	
	public void delete(MovieScreenPrice movieScreenPrice);
	
	public List<MovieScreenPrice> getMovieScreenPrices(Integer movieScreenId);
	
}
