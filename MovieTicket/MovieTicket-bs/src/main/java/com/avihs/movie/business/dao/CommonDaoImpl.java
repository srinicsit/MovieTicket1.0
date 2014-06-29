package com.avihs.movie.business.dao;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
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

	public boolean isSkipClass(Class clazz) {
		Class[] skippedClasses = new Class[] {
		// classes to skip
		};
		return Arrays.asList(skippedClasses).contains(clazz);
	}

	protected void forceLoad(Object entity) throws HibernateException {
		if (entity == null) {
			return;
		}

		if (isSkipClass(entity.getClass())) {
			return;
		}

		ClassMetadata classMetadata = getSessionFactory().getClassMetadata(
				entity.getClass());

		if (classMetadata == null) {
			return;
		}

		Hibernate.initialize(entity);

		for (int i = 0, n = classMetadata.getPropertyNames().length; i < n; i++) {
			String propertyName = classMetadata.getPropertyNames()[i];
			Type type = classMetadata.getPropertyType(propertyName);

			if (type.isEntityType()) {
				Object subEntity = classMetadata.getPropertyValue(entity,
						propertyName);
				forceLoad(subEntity);

			}
			if (type.isCollectionType()) {
				Collection collection = (Collection) classMetadata
						.getPropertyValue(entity, propertyName);
				if (collection != null && collection.size() > 0) {
					for (Object collectionItem : collection) {
						forceLoad(collectionItem);
					}
				}
			}
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
