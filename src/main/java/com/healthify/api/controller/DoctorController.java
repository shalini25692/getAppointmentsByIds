package com.healthify.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthify.api.entity.DoctorsTimeOff;
import com.healthify.api.service.DoctorService;


/**
 * @author RAM
 *
 */
@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	private static Logger log = LogManager.getLogger(DoctorController.class);
	
	@Autowired
	private DoctorService service;
	
	@PostMapping(value = "/set-time-off",produces = {"application/json"})
	public ResponseEntity<String> setTimeOff(@RequestBody @Valid DoctorsTimeOff doctorsTimeOff) 
	{
		System.out.println(doctorsTimeOff);
		int status = service.setTimeOff(doctorsTimeOff);

		if (status == 2)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("doctorsTimeOff Updated");
		}
		else
			if (status == 1) 
			{

				return ResponseEntity.status(HttpStatus.CREATED).body("doctorsTimeOff Sucesssfully Saved");
			}
			else 
			{

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
			}

	}
	
	
	@GetMapping(value = "/get-time-off/{doctorUsername}",produces = {"application/json"})
	public ResponseEntity<Object> getDoctorTimeOff(@PathVariable String doctorUsername)
	{
		List<DoctorsTimeOff> doctorTimeOff = service.getDoctorTimeOff(doctorUsername);
		
		if(doctorTimeOff!=null)
		{
			 return ResponseEntity.ok(doctorTimeOff);
		}
		
		else
		{
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Username");
		}
	}
	
}
