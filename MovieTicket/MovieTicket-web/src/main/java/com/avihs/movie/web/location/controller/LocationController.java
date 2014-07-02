package com.avihs.movie.web.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avihs.movie.business.location.model.Location;
import com.avihs.movie.business.location.service.LocationService;

@Controller
@RequestMapping("locations")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/fullList", method = RequestMethod.GET)
	public @ResponseBody
	List<Location> getAllLocations() {
		return locationService.getAllLocations();
	}

	@RequestMapping(value = "/partialList", method = RequestMethod.GET)
	public @ResponseBody
	List<Location> getAllLocations(@RequestParam("term") String partialLocation) {
		return locationService.getLocations(partialLocation);
	}

}
