package com.avihs.movie.business.theater.dao;

import java.util.List;

import com.avihs.movie.business.theater.model.Theater;

public interface TheaterMgmtDao {

	public void create(Theater theater);

	public void update(Theater theater);

	public Theater getTheater(Integer id);

	public List<Theater> getTheaters(String location);
}
