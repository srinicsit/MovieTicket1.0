package com.avihs.movie.business.movie.service;

import java.util.List;

import com.avihs.movie.business.movie.model.Movie;

public interface MovieService {

	public List<Movie> getAllMovies();

	public void save(Movie movie);

	public void update(Movie movie);

	public void delete(Movie movie);
	

	public boolean isMovieExists(String movieName);

	public boolean isMovieExists(String movieName, Integer movieId);
}
