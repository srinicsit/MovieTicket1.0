package com.avihs.movie.business.movie_screen_price.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;

public interface MovieScreenPriceDao extends CommonDao{

	public List<MovieScreenPrice> getMovieScreenPrices(Integer movieScreenId);
}
