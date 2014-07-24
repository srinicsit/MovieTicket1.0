package com.avihs.movie.business.movie_screen_price.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.movie_screen_price.dao.MovieScreenPriceDao;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;

@Service
public class MovieScreenPriceServiceImpl implements MovieScreenPriceService {

	@Autowired
	private MovieScreenPriceDao movieScreenPriceDao;

	@Override
	public void save(MovieScreenPrice movieScreenPrice) {
		movieScreenPriceDao.save(movieScreenPrice);
	}

	@Override
	public void update(MovieScreenPrice movieScreenPrice) {
		movieScreenPriceDao.update(movieScreenPrice);
	}

}
