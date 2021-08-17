package com.stackroute.airwatcher.model;

public class City {
	
	String cityid;
	String cityname;
	
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Override
	public String toString() {
		return "City [cityid=" + cityid + ", cityname=" + cityname + "]";
	}
	
	
}
