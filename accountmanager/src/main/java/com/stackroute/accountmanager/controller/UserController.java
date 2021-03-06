package com.stackroute.accountmanager.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.model.UserProfile;
import com.stackroute.accountmanager.service.UserService;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
@RequestMapping("/auth/user")
public class UserController {
	
	@Autowired
	UserService uservice;
	
	@PostMapping("/adduser")
	public ResponseEntity<?> storeUser(@RequestBody UserProfile uprofile){
		
		try {
			UserProfile result = uservice.addUser(uprofile);
			return new ResponseEntity<UserProfile>(result, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		 
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserProfile uprofile){
		UserProfile result = uservice.validateLogin(uprofile.getEmailid(),uprofile.getPassword());
		if(result == null) {
			return new ResponseEntity<String>("Invalid User", HttpStatus.UNAUTHORIZED);
		}
		String tokendata = generateToken(result);
		HashMap hashmap = new HashMap();
		hashmap.put("token", tokendata);
		return new ResponseEntity<HashMap>(hashmap,HttpStatus.OK);
	}
	
	public String generateToken(UserProfile uprofile) {
		long duration = 10_000_00;
		//sending the payload
		return Jwts.builder().setSubject(uprofile.getEmailid())
							 .setIssuedAt(new Date(System.currentTimeMillis()))
							 .setExpiration(new Date(System.currentTimeMillis()+duration))
							 .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,"ibmfsd")
							 .compact();
	}
}
