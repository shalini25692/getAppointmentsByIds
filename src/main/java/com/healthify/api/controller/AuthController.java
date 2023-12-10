package com.healthify.api.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthify.api.aop.TrackExecutionTime;
import com.healthify.api.entity.User;
import com.healthify.api.model.ResetPasswordDetail;
import com.healthify.api.security.CustomUserDetailService;
import com.healthify.api.service.EmailPasswordService;
import com.healthify.api.service.UserService;
import com.healthify.api.utility.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static Logger LOG = LogManager.getLogger(AuthController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	EmailPasswordService emailPasswordService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// completed
	@PostMapping("/login-user")
	@TrackExecutionTime
	public ResponseEntity<?> loginAdmin(@RequestBody User user,HttpServletResponse response) throws AuthenticationException {

		
		return null;
    
    }
	
	@PostMapping(value = "/reset-password-by-qa")
	public ResponseEntity<String> resetPasswordByQA(@RequestBody  ResetPasswordDetail detail) {
		return null;
	}

	@PostMapping(value = "/reset-password-by-otp")
	public ResponseEntity<String> resetPasswordByOtp(@RequestBody  ResetPasswordDetail detail) {
		return null;
	}
	
	@GetMapping(value = "/send-otp/{userId}")
	public ResponseEntity<String> sendOtp(@PathVariable String userId){
		return null;
	}

}
