package com.avihs.movie.business.movie_screen_price.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.movie_screen_price.model.MovieScreenPrice;

@Repository
public class MovieScreenPriceDaoImpl extends CommonDaoImpl implements
		MovieScreenPriceDao {

	@Override
	public List<MovieScreenPrice> getMovieScreenPrices(Integer movieScreenId) {

		Query query = getCurrentSession().getNamedQuery("getMovieScreenPrice");
		query.setInteger("movieScreenId", movieScreenId);
		return query.list();
	}

}
