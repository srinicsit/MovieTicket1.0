package com.avihs.movie.business.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.location.dao.LocationDao;
import com.avihs.movie.business.location.model.Location;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;

	@Override
	public List<Location> getAllLocations() {
		return locationDao.get(Location.class);
	}

	@Override
	public List<Location> getLocations(String partialLoc) {
		return locationDao.getLocations(partialLoc);
	}

}
