package com.nagarro.hrmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.hrmanagement.entities.User;
import com.nagarro.hrmanagement.interfaces.UserInterface;
import com.nagarro.hrmanagement.repos.UserRepo;

@Service
public class UserService implements UserInterface {
	@Autowired
	private UserRepo userRepository;

	@Override
	public User login(User user) {
		 List<User> users = userRepository.findAll();
		 for(User authuser: users) {
			 if(authuser.getUsername().equals(user.getUsername()) && authuser.getPassword().equals(user.getPassword())) {
				 return user;
			 }
		 }
		 return null;
	}
}
