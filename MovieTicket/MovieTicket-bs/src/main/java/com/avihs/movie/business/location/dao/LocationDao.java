package com.avihs.movie.business.location.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.location.model.Location;

public interface LocationDao extends CommonDao {
	
	public List<Location> getLocations(String partialLoc);
}
