package com.avihs.movie.business.screen.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.screen.model.Screen;

@Repository
public class ScreenDaoImpl<Screen> extends CommonDaoImpl<Screen> implements
		ScreenDao<Screen> {

	public List<Screen> getScreens(Integer theaterId) {

		Query query = getCurrentSession().getNamedQuery("getScreensForTheater");
		query.setInteger("theaterId", theaterId);
		return query.list();
	}

}
