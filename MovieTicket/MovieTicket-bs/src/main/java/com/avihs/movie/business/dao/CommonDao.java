package com.avihs.movie.business.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

public class CommonDao {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

}
