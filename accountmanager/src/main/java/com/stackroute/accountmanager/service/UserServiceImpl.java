package com.stackroute.accountmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.dao.UserRepo;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.model.UserProfile;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userrepo;

	@Override
	public UserProfile addUser(UserProfile uobj) throws UserAlreadyExistsException {
		Optional<UserProfile> existuser = userrepo.findById(uobj.getEmailid());
		if(existuser.isPresent()) {
			throw new UserAlreadyExistsException("Email id already registered. Please login");
		}
		userrepo.save(uobj);
		return uobj;
	}

	@Override
	public UserProfile validateLogin(String mailid, String pwd) {
		UserProfile uobj = userrepo.findByEmailidAndPassword(mailid, pwd);
		return uobj;
	}

}
