package com.stackroute.airwatcher.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WatchedCity {

	@Id
	String userid;
	List<City> cities;
	
	public WatchedCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WatchedCity(String userid2, List<City> citylist) {
		this.userid = userid2;
		this.cities = citylist;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		return "WatchedCity [userid=" + userid + ", cities=" + cities + "]";
	}
	
	
}
