package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import com.cashnex.dao.BannedUserDao;
import com.cashnex.dao.PendingUserInfoDao;
import com.cashnex.dao.UserDao;
import com.cashnex.dao.pending_user;
import com.cashnex.model.UserRegistration;
import com.cashnex.security.Security;
import com.cashnex.service.AccountNumberGenerator;
import com.cashnex.service.mail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserRegistration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PendingUserInfoDao pendingUserInfoDao;
	public UserDao userDao;
	public BannedUserDao bannedUserDao;

	public UserRegistrationServlet() {
		super();
		pendingUserInfoDao = new PendingUserInfoDao();
		userDao = new UserDao();
		bannedUserDao = new BannedUserDao();
	}

	public static String generateOTP(int length) {
		// Define characters allowed in the OTP code
		String chars = "0123456789";
		// Initialize a random object
		Random random = new Random();
		// Initialize a StringBuilder to store the OTP code
		StringBuilder otp = new StringBuilder();

		// Generate the OTP code by randomly selecting characters from the 'chars'
		// string
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(chars.length());
			otp.append(chars.charAt(index));
		}

		// Return the OTP code as a string
		return otp.toString();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/views/userRegistration.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String nrcNumber = request.getParameter("nrcNumber");
		String userGmail = request.getParameter("userGmail");
		String career = request.getParameter("career");
		String userPassword = request.getParameter("userPassword");

		String hashedPassword = Security.doHashing(userPassword);
		String accountNumber = AccountNumberGenerator.generateAccountNumber();

		pending_user storeUser = new pending_user();
		UserRegistration ur = new UserRegistration();
		
		

		List<String> bannedUserGmails = null;
		List<String> originalUserGmails = null;
		try {
			bannedUserGmails = bannedUserDao.getBannedUserEmails();
			originalUserGmails = userDao.getOriginalUserEmails();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String string : bannedUserGmails) {
			System.out.println(string);
		}

		
		
		if (originalUserGmails.contains(userGmail)) {
			
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		} 
		if (bannedUserGmails.contains(userGmail)) {
			request.setAttribute("bannedEmail", userGmail);
			request.getRequestDispatcher("/views/userRegistration.jsp").forward(request, response);
		} else {
			try {

				String verificationToken = generateOTP(6);

				storeUser.deletePendingUser();

				ur.setUsername(userName);
				ur.setNrcNumber(nrcNumber);
				ur.setUserEmail(userGmail);
				ur.setCareer(career);
				ur.setHashedPassword(hashedPassword);
				ur.setAccountNumber(accountNumber);
				ur.setOtp(verificationToken);

				storeUser.addUserRegistration(ur);

				String content = "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px;\">"
						+ "<h1 style=\"color: #333;\">Hello!</h1>"
						+ "<h5 style=\"color: #555;\">Welcome to CashNex.com</h5>"
						+ "<p style=\"color: #555;\"> User Name         :  " + userName + "</p>"
						+ "<p style=\"color: #555;\"> Verification code :  " + verificationToken + "</p>"
						+ "<p style=\"color: #555;\">Thank you!</p>" + "</div>";

				mail.sendEmail(userGmail, "Email Verification", content);

				request.setAttribute("notificationMessage", "Registration successful!");
				request.setAttribute("registrationSuccess", true);
				request.getRequestDispatcher("/views/OTP.jsp").forward(request, response);
				
				
			} catch (MessagingException e) {
				e.printStackTrace();
				// If an error occurs, set error message attribute
				request.setAttribute("notificationMessage", "Registration failed. Please try again later.");
				request.setAttribute("registrationError", true);
			}
			request.getRequestDispatcher("/views/userRegistration.jsp").forward(request, response);
		}

		response.sendRedirect(request.getContextPath() + "/views/userLogin.jsp");
	}

}
