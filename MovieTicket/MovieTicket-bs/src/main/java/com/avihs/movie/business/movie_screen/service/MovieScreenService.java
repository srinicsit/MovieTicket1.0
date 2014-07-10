package com.avihs.movie.business.movie_screen.service;

import java.util.Date;
import java.util.List;

import com.avihs.movie.business.movie_screen.model.MovieScreen;

public interface MovieScreenService {

	public void save(MovieScreen movieScreen);

	public void update(MovieScreen movieScreen);

	public void delete(MovieScreen movieScreen);

	public MovieScreen loadMovieScreen(Integer movieScreenId);

	public List<MovieScreen> getMovieScreens(Integer movieId);

	public List<MovieScreen> getMovieScreens(Integer movieId, Date showDate);

}
