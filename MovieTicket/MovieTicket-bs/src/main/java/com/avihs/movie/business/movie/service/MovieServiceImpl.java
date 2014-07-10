package com.avihs.movie.business.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.movie.dao.MovieDao;
import com.avihs.movie.business.movie.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Transactional(readOnly = true)
	public List<Movie> getAllMovies() {
		return movieDao.get(Movie.class);
	}

	@Override
	@Transactional
	public void save(Movie movie) {
		movieDao.save(movie);

	}

	@Override
	@Transactional
	public void update(Movie movie) {
		movieDao.update(movie);

	}

	@Override
	@Transactional
	public void delete(Movie movie) {
		movieDao.delete(movie);

	}

	@Override
	@Transactional(readOnly = true)
	public boolean isMovieExists(String movieName) {
		return movieDao.isMovieExists(movieName);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isMovieExists(String movieName, Integer movieId) {
		return movieDao.isMovieExists(movieName, movieId);
	}

	@Override
	public Movie loadMovie(Integer movieId) {
		return movieDao.load(Movie.class, movieId);

	}
}
