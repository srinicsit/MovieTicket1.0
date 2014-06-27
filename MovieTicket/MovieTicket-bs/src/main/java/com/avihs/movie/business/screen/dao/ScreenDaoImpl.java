package com.avihs.movie.business.screen.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.screen.model.Screen;
import com.avihs.movie.business.theater.model.Theater;
import com.avihs.movie.business.user.model.User;

@Repository
public class ScreenDaoImpl extends CommonDaoImpl implements ScreenDao {

	public List<Screen> getScreens(Integer theaterId) {

		// Query query =
		// getCurrentSession().getNamedQuery("getScreensForTheater");
		// query.setInteger("theaterId", theaterId);
		// return query.list();

		Criteria cr = getCurrentSession()
				.createCriteria(Screen.class)
				.setProjection(
						Projections.projectionList()
								.add(Projections.property("id"), "id")
								.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Screen.class));

		Theater theater = new Theater();
		theater.setId(theaterId);
		cr.add(Restrictions.eq("theater", theater));

		List<Screen> list = cr.list();
		return list;
	}

}
