package com.cg.user.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.cg.user.dto.UserCredentials;
import com.cg.user.entity.User;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.WrongPasswordException;
import com.cg.user.repository.UserRepository;





@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	

	public User userSignUp(User user) throws CustomerAlreadyExistsException {
		User user1 = userRepository.findByUserName(user.getUserName());
		if (user1 != null) {
			throw new CustomerAlreadyExistsException("UserName already exists.");
		} else {
			userRepository.save(user);
			return user;
		}
	}

	public User userSignIn(User user) throws WrongPasswordException {
		User user1 = userRepository.findByUserName(user.getUserName());
		if (user.getPassword().equals(user1.getPassword())) {
			return user1;
		} else {
			throw new WrongPasswordException("Wrong Password");
		}
	}
	
	public UserCredentials getUserByUsername(String  userName)  {
		User user1 = userRepository.findByUserName(userName);
		UserCredentials u=new UserCredentials();
		u.setUserName(user1.getUserName());
		u.setPassword(user1.getPassword());
		
		return u;
		
	}
	
	

}
