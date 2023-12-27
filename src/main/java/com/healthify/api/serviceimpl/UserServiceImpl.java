package com.healthify.api.serviceimpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthify.api.dao.UserDao;
import com.healthify.api.entity.Otp;
import com.healthify.api.entity.Role;
import com.healthify.api.entity.User;
import com.healthify.api.security.CustomUserDetail;
import com.healthify.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	/*
	 * @Autowired private JavaMailSender emailSender;
	 */

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDao dao;

	@Value("${user.roles}")
	private String[] roles;

	@Override
	public boolean addUser(User user) {
		boolean addUser = dao.addUser(user);
		return addUser;
	}

	@Override
	public User loginUser(User user) {

		return dao.loginUser(user);
	}

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {
		return dao.loadUserByUserId(userId);
	}

	@Override
	public boolean deleteUserById(String id) {
		return dao.deleteUserById(id);
	}

	@Override
	public User getUserById(String id) {
		User user = dao.getUserById(id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {

		return null;

	}

	@Override
	public User updateUser(User user) {
		return null;

	}

	@Override
	public Long getUsersTotalCounts() {
		return dao.getUsersTotalCounts();
	}

	@Override
	public Long getUsersTotalCounts(String type) {
		return dao.getUsersTotalCounts(type);
	}

	@Override
	public Long getUserCountByDateAndType(Date registereddate, String type) {
		return dao.getUserCountByDateAndType(registereddate, type);
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		return dao.getUserByFirstName(firstName);
	}

	@Override
	public Role addRole(Role role) {

		return null;

	}

	@Override
	public Role getRoleById(int roleId) {

		return dao.getRoleById(roleId);
	}

	@Override
	public String generateReport() {

		return "generated";
	}

	/*
	 * public boolean saveOtp(String email) { User userByEmail =
	 * dao.getUserByEmail(email);
	 * 
	 * if (userByEmail != null) { Otp otp = new Otp();
	 * 
	 * String otpGen = generateOTP();
	 * 
	 * otp.setUserId(userByEmail.getUsername());
	 * otp.setOtp(Integer.parseInt(otpGen)); otp.setTimestamp(new
	 * Timestamp(System.currentTimeMillis()));
	 * 
	 * boolean saveOtp = dao.saveOtp(otp);
	 * 
	 * if (saveOtp == true) { SimpleMailMessage message = new SimpleMailMessage();
	 * message.setTo(email); message.setSubject("Your OTP");
	 * message.setText("Your OTP is: " + otpGen);
	 * 
	 * emailSender.send(message); // Send the email }
	 * 
	 * return true;
	 * 
	 * }
	 * 
	 * return false;
	 * 
	 * }
	 */
	/*
	 * public String generateOTP() { int length = 6; // Define the length of the OTP
	 * 
	 * // Generate random numbers to create the OTP String numbers = "0123456789";
	 * // Characters allowed in the OTP Random random = new Random(); StringBuilder
	 * otp = new StringBuilder();
	 * 
	 * for (int i = 0; i < length; i++) { int randomIndex =
	 * random.nextInt(numbers.length()); otp.append(numbers.charAt(randomIndex)); }
	 * return otp.toString();
	 * 
	 * }
	 */

}
