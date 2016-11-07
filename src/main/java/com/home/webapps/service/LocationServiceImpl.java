package com.home.webapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.dao.LocationDao;
import com.home.webapps.domain.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public Location getLocationById(Location location) throws Exception {
		return locationDao.getLocation(location);
	}
	
	public Location getLocationByName(Location location) throws Exception {
		return locationDao.getLocationByName(location);
	}

	public List<Location> getLocations() throws Exception {
		return locationDao.getLocations();
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveLocation(Location location) throws Exception {
			
			locationDao.saveLocation(location);
	}
		 
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteLocation(Location location) throws Exception {
		
		location = locationDao.getLocation(location);
		
		if(location != null) {
			locationDao.removeLocation(location);
		}
	}

}
