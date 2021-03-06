package com.stackroute.airwatcher.service;

import java.util.List;

import com.stackroute.airwatcher.model.City;
import com.stackroute.airwatcher.model.WatchedCity;

public interface WatchedCityService {

	public boolean addCity(String userid, City city);
	
	public List<City> getAllCityByUserId(String userid);
	
	public List<WatchedCity> getAllWatchedCity();

	public boolean deleteCitybyUserId(String userid, City city);
	
	
	
}
