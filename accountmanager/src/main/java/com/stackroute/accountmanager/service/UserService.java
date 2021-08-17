package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.model.UserProfile;

public interface UserService {

	UserProfile addUser(UserProfile uobj) throws UserAlreadyExistsException;
	
	UserProfile validateLogin(String mailid, String pwd);
}
