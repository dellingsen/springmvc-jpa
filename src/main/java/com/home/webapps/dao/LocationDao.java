package com.home.webapps.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.domain.Location;

@Repository
public interface LocationDao {  
	
	public final static String PERSISTENCE_UNIT = "mysql-Unit";
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveLocation(Location location) throws Exception;

    public void removeLocation(Location location) throws Exception;
    
    public Location getLocation(Location location) throws Exception;

    public Location getLocationByName(Location location) throws Exception;

    public List<Location> getLocations() throws Exception;
}
