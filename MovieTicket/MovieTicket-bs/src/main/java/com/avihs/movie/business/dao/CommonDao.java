package com.avihs.movie.business.dao;

public interface CommonDao<T> {

	public T get(Class cls, Integer id);

	public void save(T t);

	public void update(T t);

	public void delete(T t);
}
