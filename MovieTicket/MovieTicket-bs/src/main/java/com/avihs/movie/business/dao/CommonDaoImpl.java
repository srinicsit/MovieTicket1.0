package com.avihs.movie.business.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class CommonDaoImpl implements CommonDao {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> T get(Class cls, Integer id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().get(cls, id);
	}

	@Override
	@Transactional
	public <T> void save(T t) {
		getCurrentSession().save(t);

	}

	@Override
	@Transactional
	public <T> void update(T t) {
		getCurrentSession().update(t);
	}

	@Override
	@Transactional
	public <T> void delete(T t) {
		getCurrentSession().delete(t);

	}

	public <T> List<T> get(Class cls) {

		Criteria criteria = getCurrentSession().createCriteria(cls);
		return criteria.list();

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public <T> T load(Class cls, Integer id) {
		return (T) getCurrentSession().load(cls, id);
	}

}
