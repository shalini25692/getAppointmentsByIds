package com.healthify.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthify.api.entity.User;
import com.healthify.api.service.UserService;

/**
 * @author RAM
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController 
{

	private static Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@GetMapping(value = "get-user-by-id/{id}", produces = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable String id) 
	{
		return null;
	}
	
	
	@GetMapping(value = "get-users-total-counts", produces = "application/json")
	public ResponseEntity<Long> getUsersTotalCounts() 
	{
		Long usersTotalCounts = userService.getUsersTotalCounts();
		
		return ResponseEntity.ok(usersTotalCounts );
		
	}

	@GetMapping(value = "get-all-users", produces = "application/json")
	public ResponseEntity<?> getAllUsers() 
	{
		List<User> list = userService.getAllUsers();
		
		if(!list.isEmpty())
		{
			 return ResponseEntity.ok(list);
		}
		
		else
		{
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Table is Empty");
		}
	
	}

	

}
