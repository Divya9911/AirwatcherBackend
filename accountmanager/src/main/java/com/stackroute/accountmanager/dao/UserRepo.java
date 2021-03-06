package com.stackroute.accountmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.accountmanager.model.UserProfile;


@Repository
public interface UserRepo extends JpaRepository<UserProfile, String>{
	
	UserProfile findByEmailidAndPassword(String email,String pwd);
	
}
