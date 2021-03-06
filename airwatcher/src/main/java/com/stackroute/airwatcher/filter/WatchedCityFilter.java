package com.stackroute.airwatcher.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class WatchedCityFilter extends GenericFilter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		
		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
		httpresponse.setHeader("Access-Control-Allow-Method", "POST,GET,DELETE,PUT,OPTIONS");
		httpresponse.setHeader("Access-Control-Allow-Credential","true");
		
		String authheader = httprequest.getHeader("Authorization");
		
		System.out.println(authheader);
		
		if(authheader == null || (!authheader.startsWith("Bearer"))) {
			//Here you can create user defined exception also
			throw new ServletException("JWT token exception");
		}
		
		String token = authheader.substring(7);
		
		//Checking validity
		try {
			//Here ibmfsd is the key that we have provided in usercontroller.
			JwtParser jparser = Jwts.parser().setSigningKey("ibmfsd");
			Jwt jwtobj =jparser.parse(token);
			Claims claim = (Claims) jwtobj.getBody();
			System.out.println("user logged in is: "+ claim.getSubject());
		}catch(SignatureException e) {
			throw new ServletException("signature mismatch");
			//System.out.println("Signature error" +e);
		}
		catch(MalformedJwtException e) {
			System.out.println("Invalid token " +e );
		}
		//For doing this activity continuously
		chain.doFilter(httprequest, httpresponse);
}
}