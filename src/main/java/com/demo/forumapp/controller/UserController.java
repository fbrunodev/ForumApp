package com.demo.forumapp.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.forumapp.models.UserModel;
import com.demo.forumapp.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/allUsers")
	public List<UserModel> getAllUsers() throws InterruptedException, ExecutionException {
		return userService.getALlUsers();
	}
	
	@GetMapping("/userDetails")
	public UserModel getUserDetails(@RequestHeader() String name) throws InterruptedException, ExecutionException {
		return userService.getUserDetails(name);
	}
	
	@PostMapping("/createUser")
	public String createUser(@RequestBody UserModel user) throws InterruptedException, ExecutionException {
		return userService.createUser(user);
	}
	
	@PutMapping("/updateUser")
	public String updateUser(@RequestBody UserModel user) throws InterruptedException, ExecutionException {
	
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestHeader() String name) throws InterruptedException, ExecutionException {
		return userService.deleteUser(name);
	}
}

