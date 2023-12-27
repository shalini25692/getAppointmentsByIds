// Java Program to Illustrate Creation Of
// Service implementation class

package com.healthify.api.serviceimpl;

import java.sql.Timestamp;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.healthify.api.dao.UserDao;
import com.healthify.api.entity.Otp;
import com.healthify.api.entity.User;
import com.healthify.api.model.EmailDetails;
import com.healthify.api.model.ResetPasswordDetail;
import com.healthify.api.service.EmailPasswordService;
import com.healthify.api.service.UserService;

@Service
public class EmailPasswordServiceImpl implements EmailPasswordService {

	@Autowired
	private JavaMailSender emailSender;

	private static Logger LOG = LogManager.getLogger(EmailPasswordServiceImpl.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Value("${spring.mail.username}")
	private String sender;

	// Method 1
	// To send a simple email
	public boolean sendMail(EmailDetails details) {
		boolean isSent = false;

		return isSent;
	}

	@Override
	public String resetPasswordByQA(ResetPasswordDetail detail) {
		String message = null;

		return message;
	}

	@Override
	public String sendOtp(String UserId) {

		User userByEmail = userDao.getUserById(UserId);

		if (userByEmail != null) {
			Otp otp = new Otp();

			String otpGen = generateOTP();

			otp.setUserId(userByEmail.getUsername());
			otp.setOtp(Integer.parseInt(otpGen));
			otp.setTimestamp(new Timestamp(System.currentTimeMillis()));

			boolean saveOtp = userDao.saveOtp(otp);

			if (saveOtp == true) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(userByEmail.getEmailid());
				message.setSubject("Your OTP");
				message.setText("Your OTP is: " + otpGen);

				emailSender.send(message); // Send the email
			}

			return userByEmail.getUsername();

		}

		return null;

	}

	@Override
	public String resetPasswordByOtp(ResetPasswordDetail detail) {

		Otp otpByUser = userDao.getOtpByUser(detail.getUserId());
		if (otpByUser != null && otpByUser.getOtp() == detail.getOtp()) {
			User user = userDao.getUserById(otpByUser.getUserId());
			user.setPassword(detail.getNewPassword());
			User updateUser = userDao.updateUser(user);
			return otpByUser.getUserId();
		}

		return null;

	}

	public String generateOTP() {
		int length = 6; // Define the length of the OTP

		// Generate random numbers to create the OTP
		String numbers = "0123456789"; // Characters allowed in the OTP
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(numbers.length());
			otp.append(numbers.charAt(randomIndex));
		}
		return otp.toString();

	}

}
