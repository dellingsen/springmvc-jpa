package com.home.webapps.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.webapps.domain.Location;
import com.home.webapps.service.LocationService;
import com.home.webapps.ui.form.LocationForm;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(value = { "/getLocations" }, method = RequestMethod.GET)
	protected ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		List<Location> locationList = new ArrayList<Location>();

		try {
			// Get list of locations from DB
			locationList = locationService.getLocations();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("locations");

		mav.addObject("locationList", locationList);
		mav.addObject("location", new Location());
		return mav;
	}

	@RequestMapping(value = { "/saveLocation" }, method = RequestMethod.POST)
	protected ModelAndView addLocation(
			@ModelAttribute("location") LocationForm locationForm,
			BindingResult result) throws Exception {

		// Keep the UI and Model separate
		Location location = new Location();
		location.setName(locationForm.getName());
		location.setAddress(locationForm.getAddress());
		location.setLatitude(locationForm.getLatitude());
		location.setLongitude(locationForm.getLongitude());
		location.setType(locationForm.getType());

		try {
			locationService.saveLocation(location);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("locations");
		mav.addObject("locationList", locationService.getLocations());
		mav.addObject("location", new LocationForm());
		return mav;
	}

	@RequestMapping(value = { "/removeLocation" }, method = RequestMethod.GET)
	protected ModelAndView removeLocation(HttpServletRequest request)
			throws Exception {

		Location location = new Location();
		location.setId(Integer.valueOf(request.getParameter("id")));		

		try {
			locationService.deleteLocation(location);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("locations");
		mav.addObject("locationList", locationService.getLocations());
		mav.addObject("location", new LocationForm());
		return mav;
	}

	@RequestMapping(value = "/rest/locations", method = RequestMethod.GET, produces = "application/json")
	// @GET
	// @Path("/rest/locations")
	// @Produces(MediaType.APPLICATION_JSON)
	protected @ResponseBody
	List<Location> getLocations() throws Exception {
		List<Location> locationList = new ArrayList<Location>();

		try {
			// Get list of locations from DB
			locationList = locationService.getLocations();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return locationList;
	}

	@RequestMapping(value = "/rest/location/{name}", method = RequestMethod.GET, produces = "application/json")
	protected @ResponseBody
	Location getLocation(@PathVariable String name) throws Exception {

		Location location = new Location();
		location.setName(name);

		try {
			// retrieve location info by name
			location = locationService.getLocationByName(location);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}
	
	@RequestMapping(value = "/rest/location/{id}", method = RequestMethod.GET, produces = "application/json")
	protected @ResponseBody
	Location getLocationById(@PathVariable int id) throws Exception {

		Location location = new Location();
		location.setId(id);

		try {
			// retrieve location info by id
			location = locationService.getLocationById(location);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}
}