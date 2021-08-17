package com.stackroute.airwatcher.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.airwatcher.model.WatchedCity;

@Repository
public interface WatchedCityRepo extends MongoRepository<WatchedCity, String>{

	//Optional<WatchedCity> findByUserid(String userid);
	
	
}
