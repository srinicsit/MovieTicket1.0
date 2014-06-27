package com.avihs.movie.business.dao;

public interface CommonDao {

	public <T> T get(Class cls, Integer id);

	public <T> void save(T t);

	public <T> void update(T t);

	public <T> void delete(T t);
}
