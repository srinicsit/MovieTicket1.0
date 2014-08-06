package com.avihs.movie.business.movie_screen_price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.movie_screen_price.dao.MovieScreenPriceDao;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;

@Service
public class MovieScreenPriceServiceImpl implements MovieScreenPriceService {

	@Autowired
	private MovieScreenPriceDao movieScreenPriceDao;

	@Override
	@Transactional
	public void save(MovieScreenPrice movieScreenPrice) {
		movieScreenPriceDao.save(movieScreenPrice);
	}

	@Override
	@Transactional
	public void update(MovieScreenPrice movieScreenPrice) {
		movieScreenPriceDao.update(movieScreenPrice);
	}

	@Override
	public MovieScreenPrice getMovieScreenPrice(Integer movieScreenPriceId) {
		return movieScreenPriceDao.get(MovieScreenPrice.class,
				movieScreenPriceId);
	}

	@Override
	public MovieScreenPrice loadMovieScreenPrice(Integer movieScreenPriceId) {
		return movieScreenPriceDao.load(MovieScreenPrice.class,
				movieScreenPriceId);
	}

	@Transactional
	public void delete(MovieScreenPrice movieScreenPrice) {
		movieScreenPriceDao.delete(movieScreenPrice);
	}

	@Override
	public List<MovieScreenPrice> getMovieScreenPrices(Integer movieScreenId) {
		return movieScreenPriceDao.getMovieScreenPrices(movieScreenId);
	}

}
