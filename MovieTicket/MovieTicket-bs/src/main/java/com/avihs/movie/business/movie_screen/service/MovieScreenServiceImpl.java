package com.avihs.movie.business.movie_screen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.movie_screen.dao.MovieScreenDao;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Service
public class MovieScreenServiceImpl implements MovieScreenService {

	@Autowired
	private MovieScreenDao movieScreenDao;

	@Transactional
	@Override
	public void save(MovieScreen movieScreen) {
		movieScreenDao.save(movieScreen);
	}

	@Transactional
	@Override
	public void update(MovieScreen movieScreen) {
		movieScreenDao.update(movieScreen);
	}

	@Transactional
	@Override
	public void delete(MovieScreen movieScreen) {
		movieScreenDao.delete(movieScreen);
	}

	@Override
	public List<MovieScreen> getMovieScreens(Integer movieId) {
		return movieScreenDao.getMovieScreens(movieId);
	}

	@Override
	public MovieScreen loadMovieScreen(Integer movieScreenId) {
		return movieScreenDao.load(MovieScreen.class, movieScreenId);
	}

	@Override
	public List<MovieScreen> getMovieScreens(Integer movieId,Date showDate) {
		return movieScreenDao.getMovieScreens(movieId,showDate);
	}

	@Override
	public List<MovieScreen> getMovieScreensForScreen(Integer screenId) {
		 return movieScreenDao.getMovieScreensForScreen(screenId);
	}

}
