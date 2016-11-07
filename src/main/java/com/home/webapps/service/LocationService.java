package com.home.webapps.service;

import java.util.List;

import com.home.webapps.domain.Location;

public interface LocationService {

	public Location getLocationById(Location location) throws Exception;
	
	public Location getLocationByName(Location location) throws Exception;

	public List<Location> getLocations() throws Exception;

	public void saveLocation(Location location) throws Exception;

	public void deleteLocation(Location location) throws Exception;
}
