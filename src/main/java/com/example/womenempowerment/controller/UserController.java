package com.example.womenempowerment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.womenempowerment.entity.User;
import com.example.womenempowerment.exception.UserAlreadyExistsException;
import com.example.womenempowerment.exception.UserDoesNotExistsException;
import com.example.womenempowerment.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userServ;

	@PostMapping("/User")
	public ResponseEntity<User> registerUser(@RequestBody User User) throws UserAlreadyExistsException {
		User ts = userServ.registerUser(User);
		return new ResponseEntity<User>(ts, HttpStatus.ACCEPTED);
	}

	
	@PostMapping("/Userlogin")
	public ResponseEntity<User> login(@RequestBody User user) throws UserDoesNotExistsException {
		User data = userServ.login(user);
		return new ResponseEntity<User>(data,HttpStatus.ACCEPTED);
	}

	@GetMapping("/UserforgotPassword")
	public ResponseEntity<User> forgotPassword(@RequestBody User user) throws UserDoesNotExistsException {
		User us = userServ.forgotPassword(user);
		return new ResponseEntity<User>(us, HttpStatus.OK);
	}
}
