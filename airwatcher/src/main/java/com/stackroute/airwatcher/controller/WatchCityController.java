package com.stackroute.airwatcher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.airwatcher.model.City;
import com.stackroute.airwatcher.model.WatchedCity;
import com.stackroute.airwatcher.service.WatchedCityService;

@RestController
@RequestMapping("/api/v1")
public class WatchCityController {

	WatchedCityService cityservice;
	
	@Autowired
	public WatchCityController(WatchedCityService cityservice) {
		this.cityservice = cityservice;
	}
	
	@PostMapping("/addCity/{userid}")
	public ResponseEntity<?> addNews(@RequestBody City city, @PathVariable("userid") String userid){
	
			boolean result = cityservice.addCity(userid,city);
			if(result) {
				return new ResponseEntity<City>(city, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("City not added", HttpStatus.CONFLICT);
			}
		
	}
	
	@GetMapping("/city/{userid}")
	public ResponseEntity<?> getAllCityByUserid(@PathVariable("userid") String userid){
		System.out.println("inside controller");
		List<City> result = cityservice.getAllCityByUserId(userid);
		System.out.println("After calling service method");
		if(result == null) {
			return new ResponseEntity<String>("City not found", HttpStatus.NOT_FOUND);
		}
		else 
		return new ResponseEntity<List<City>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/allCities")
	public ResponseEntity<?> getAllWatchedCities(){
		List<WatchedCity> cities = cityservice.getAllWatchedCity();
		return new ResponseEntity<List<WatchedCity>>(cities,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/deleteWatchedCity/{userid}")
	public ResponseEntity<?> deleteWatchedCity(@RequestBody City city,@PathVariable("userid") String userid){
		cityservice.deleteCitybyUserId(userid, city);
		return new ResponseEntity<String>("All news deleted",HttpStatus.OK);
	}
}
