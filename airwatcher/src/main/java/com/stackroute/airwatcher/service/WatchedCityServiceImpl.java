package com.stackroute.airwatcher.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.airwatcher.dao.WatchedCityRepo;
import com.stackroute.airwatcher.model.City;
import com.stackroute.airwatcher.model.WatchedCity;

@Service
public class WatchedCityServiceImpl implements WatchedCityService{
	
	WatchedCityRepo cityrepo;
	
	@Autowired
	public WatchedCityServiceImpl(WatchedCityRepo cityrepo){
		this.cityrepo = cityrepo;
	}

	@Override
	public boolean addCity(String userid, City city) {
		boolean result = false;
		try {
			Optional<WatchedCity> optcity =cityrepo.findById(userid);
			if(optcity.isPresent()) {
				WatchedCity existwatchedcity= optcity.get();
				List<City> existcities = existwatchedcity.getCities();
				existcities.add(city);
				existwatchedcity.setCities(existcities);
				cityrepo.save(existwatchedcity);
				result =true;
			}
			else {
				System.out.println("New user created");
				List<City> citylist = new ArrayList<>();
				citylist.add(city);
				WatchedCity watchedcity = new WatchedCity(userid,citylist);
				if(cityrepo.insert(watchedcity)!=null) {
				//System.out.println("New user Inserted");
				result=true;
				}
			}
		}catch(Exception e) {
			result =false;
		}
		return result;
	}

	
	@Override
	public List<City> getAllCityByUserId(String userid) {
		System.out.println("Inside service");
		Optional<WatchedCity> optwatchedcity = cityrepo.findById(userid);
		System.out.println("Optional : "+optwatchedcity);
		if(optwatchedcity.isPresent()) {
			WatchedCity existwatchedcity = optwatchedcity.get();
			System.out.println("Watched City : "+existwatchedcity);
			List<City> existcities = existwatchedcity.getCities();
			System.out.println("Cities are : "+existcities);
			return existcities;
		}
		return null;
	}

	@Override
	public List<WatchedCity> getAllWatchedCity() {
		List<WatchedCity> cities = cityrepo.findAll();
		return cities;
	}

	

	@Override
	public boolean deleteCitybyUserId(String userid,City city) {
		try {
			Optional<WatchedCity> optcity =cityrepo.findById(userid);
			if(optcity.isPresent()) {
				
				WatchedCity existwatchedcity= optcity.get();
				System.out.println("existwatchedcity :" +existwatchedcity);
				List<City> existcities = existwatchedcity.getCities();
				System.out.println("existcities :" +existcities);
				Iterator<City> itr = existcities.iterator();
				
				while(itr.hasNext()) {
					City item = itr.next();
					System.out.println("City id from item" + item.getCityid());
					System.out.println("City id from city" + city.getCityid());
					System.out.println(item.getCityid()==(city.getCityid()));
					
					String cityid = city.getCityid();
					
					System.out.println(item.getCityid().equals(cityid));
					
					if(item.getCityid().equals(cityid)) {
						System.out.println("City id " + item.getCityid());
						itr.remove();
					}
					
				}
				existwatchedcity.setCities(existcities);
				cityrepo.save(existwatchedcity);
				return true;
			}
			}catch(NoSuchElementException e) {
				throw new NoSuchElementException("City not found");
			}
			return false;
		}
		
	}
	

