package com.cg.user.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.user.dto.UserCredentials;
import com.cg.user.entity.User;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.WrongPasswordException;
import com.cg.user.serviceImpl.UserService;






@RestController
@RequestMapping("/user/User")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signUp")
	public ResponseEntity<User> signUp(@Valid @RequestBody User user, HttpSession session) throws CustomerAlreadyExistsException {
		User user1 = userService.userSignUp(user);
		if (user1 != null) {
			session.setAttribute("user", user1);
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user1, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/userlogin")
	public ResponseEntity<User> login(@Valid @RequestBody User user, HttpSession session) throws WrongPasswordException {
		User user_info = userService.userSignIn(user);
		if (user_info != null) {
			session.setAttribute("user", user_info);
			return new ResponseEntity<>(user_info, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		if (session.getAttribute("user") != null) {
			session.invalidate();
			return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("You are not logged in", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/credentials/{userName}")
	public ResponseEntity<UserCredentials> getUserByUsername(@PathVariable ("userName") String userName){
		return new ResponseEntity<>(userService.getUserByUsername(userName),HttpStatus.OK);
		
	}

}
