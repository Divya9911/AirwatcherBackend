package com.stackroute.airwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import com.stackroute.airwatcher.filter.WatchedCityFilter;

@SpringBootApplication
public class AirwatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirwatcherApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getfilterbean() {
		UrlBasedCorsConfigurationSource urlcorsobj = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsconfig = new CorsConfiguration();
		
		//Allow user to send all the request and data
		
		corsconfig.setAllowCredentials(true);
		corsconfig.addAllowedOrigin("*");
		corsconfig.addAllowedMethod("*");
		corsconfig.addAllowedHeader("*");
		
		urlcorsobj.registerCorsConfiguration("/**", corsconfig);
		FilterRegistrationBean fbean = new FilterRegistrationBean(new WatchedCityFilter());
		
		
		fbean.addUrlPatterns("/api/v1/*");
		
		return fbean;
	}

}
