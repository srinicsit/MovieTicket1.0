package com.avihs.movie.business.location.service;

import java.util.List;

import com.avihs.movie.business.location.model.Location;

public interface LocationService {

	public List<Location> getAllLocations();
	
	public List<Location> getLocations(String partialLoc);
}
