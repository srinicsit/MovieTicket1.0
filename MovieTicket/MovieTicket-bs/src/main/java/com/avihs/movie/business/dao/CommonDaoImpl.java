package com.avihs.movie.business.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class CommonDaoImpl<T> implements CommonDao<T> {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T get(Class cls, Integer id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().get(cls, id);
	}

	@Override
	@Transactional
	public void save(T t) {
		getCurrentSession().save(t);

	}

	@Override
	@Transactional
	public void update(T t) {
		getCurrentSession().update(t);

	}

	@Override
	@Transactional
	public void delete(T t) {
		getCurrentSession().delete(t);

	}

}
