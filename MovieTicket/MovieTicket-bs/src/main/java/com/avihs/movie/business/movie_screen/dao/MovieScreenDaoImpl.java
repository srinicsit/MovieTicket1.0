package com.avihs.movie.business.movie_screen.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.movie.model.Movie;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Repository
public class MovieScreenDaoImpl extends CommonDaoImpl implements MovieScreenDao {

	public List<MovieScreen> getMovieScreens(Integer movieId) {

		Query query = getCurrentSession().getNamedQuery("getMovieScreens");
		query.setInteger("movieId", movieId);
		List<MovieScreen> movieScreens = query.list();

		return movieScreens;
	}

	public List<MovieScreen> getMovieScreens(Integer movieId, Date showDate) {
		Query query = getCurrentSession().getNamedQuery(
				"getMovieScreensForDate");
		query.setInteger("movieId", movieId);
		query.setDate("startDate", showDate);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(showDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		query.setDate("endDate", calendar.getTime());
		List<MovieScreen> movieScreens = query.list();
		return movieScreens;

	}

	public List<MovieScreen> getMovieScreens1(Integer movieId, Date showDate) {
		Criteria cr = getCurrentSession()
				.createCriteria(MovieScreen.class)
				.createAlias("screen", "sc")
				.setProjection(
						Projections
								.projectionList()
								/*.add(Projections.property("id"), "id")
								.add(Projections.property("sc.theater"),
										"sc.theater")*/
								.add(Projections.property("sc.seatClassTypes"),
										"sc.seatClassTypes"))
		/*
		 * .setResultTransformer( Transformers.aliasToBean(MovieScreen.class))
		 */;
		// cr.createAlias("screen", "sc");
		// cr.createAlias("sc.theater", "th");

		Movie movie = new Movie();
		movie.setId(movieId);
		cr.add(Restrictions.eq("movie", movie));

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(showDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		cr.add(Restrictions.between("showDate", showDate, calendar.getTime()));
		List<MovieScreen> list = cr.list();
		return list;
	}

	@Override
	public List<MovieScreen> getMovieScreensForScreen(Integer screenId) {
		Query query = getCurrentSession().getNamedQuery("getMoviesForScreen");
		query.setInteger("screenId", screenId);
		return query.list();
	}

}
