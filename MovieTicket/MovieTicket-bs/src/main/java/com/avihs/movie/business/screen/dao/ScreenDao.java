package com.avihs.movie.business.screen.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.screen.model.Screen;

public interface ScreenDao<Screen> extends CommonDao<Screen> {

	public List<Screen> getScreens(Integer theaterId);
}
