package com.avihs.movie.core.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.AbstractResource;

public class PropertiesLocationsFactoryBean extends AbstractResource {

	private String externalDir;

	private List<String> locations;

	public String getExternalDir() {
		return externalDir;
	}

	public void setExternalDir(String externalDir) {
		this.externalDir = externalDir;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return locations.get(0);
	}

	public InputStream getInputStream() throws IOException {

		return this.getClass().getResourceAsStream(
				"/" + externalDir + "/" + locations.get(0));
	}
}
