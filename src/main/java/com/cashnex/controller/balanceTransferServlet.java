package com.cashnex.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cashnex.dao.UserDao;
import com.cashnex.dao.balanceTransferDao;
import com.cashnex.service.mail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transfer")
public class balanceTransferServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	balanceTransferDao balanceTransferDao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		balanceTransferDao = new balanceTransferDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> accountNumberFromDao = null;
		List<Map<String, Object>> transactionHistory = null;
		
		String senderUserGmail = (String) request.getSession().getAttribute("userGmail");
		String recipientAccNum = request.getParameter("recipientNumber");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String transferSuccess = request.getParameter("transferSuccess");

		// Retrieve user balances from the database
		double senderBalance = 0;
		try {
			senderBalance = balanceTransferDao.getUserBalance(senderUserGmail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double recipientBalance = 0;
		try {
			recipientBalance = balanceTransferDao.getUserBalancebyAccNum(recipientAccNum);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("recepitent" + recipientAccNum);
		System.out.println("amount:" + amount);
		// Check if sender has sufficient balance

		try {
			accountNumberFromDao = UserDao.getAccountNumbers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (accountNumberFromDao.contains(recipientAccNum)) {
			
			if (senderBalance >= amount) {
				// Update balances in the database
				try {
					balanceTransferDao.updateBalance(senderUserGmail, senderBalance - amount);
					balanceTransferDao.updateBalancebyAccNum(recipientAccNum, recipientBalance + amount);
					balanceTransferDao.updatetransaction(senderUserGmail, recipientAccNum, amount);
					balanceTransferDao.recordTransaction(senderUserGmail, recipientAccNum, amount);

					transactionHistory = balanceTransferDao.getTransactionHistory(senderUserGmail);
					
					// Codes below are from GPT
					List<Map<String, Object>> transactionHistory1 = balanceTransferDao.getTransactionHistory(senderUserGmail);

					// Initialize variables to store the latest transaction date and time
					Date latestTransactionDate = null;
					Time latestTransactionTime = null;
					Map<String, Object> latestTransaction = null;

					// Loop through the transaction history to find the latest transaction
					for (Map<String, Object> transaction : transactionHistory1) {
						// Convert Date to string
						Date transactionDate = (Date) transaction.get("transaction_date");
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String dateString = dateFormat.format(transactionDate);

						// Convert Time to string
						Time transactionTime = (Time) transaction.get("transaction_time");
						SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
						String timeString = timeFormat.format(transactionTime);


					    // Compare transaction date with the latest transaction date
					    if (latestTransactionDate == null || transactionDate.after(latestTransactionDate)) {
					        // If the current transaction is newer, update the latest transaction details
					        latestTransactionDate = transactionDate;
					        latestTransactionTime = transactionTime;
					        latestTransaction = transaction;
					    }
					}

					// Now latestTransaction contains the latest transaction details
					if (latestTransaction != null) {
					    // Use latestTransactionDate and latestTransactionTime as needed
					    // Access other transaction details using latestTransaction map
					}

					
					try {
						
						String recieverUserGmail = UserDao.getUserGmailByAccNumber(recipientAccNum);

						String senderContent =  "<html lang=\"en\">\n\n"
			                    + "<head>\n"
			                    + "    <meta charset=\"UTF-8\">\n"
			                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
			                    + "    <title>Receipt</title>\n"
			                    + "    <style>\n"
			                    + "        body {\n"
			                    + "            font-family: Arial, sans-serif;\n"
			                    + "            font-weight: 1.2em;\n"
			                    + "        }\n\n"
			                    + "        .receipt {\n"
			                    + "            width: 100%;\n"
			                    + "            max-width: 500px;\n"
			                    + "            text-align: center;\n"
			                    + "            margin: 20px auto;\n"
			                    + "            padding: 10px;\n"
			                    + "            border: 1px solid #000;\n"
			                    + "            border-radius: 10px;\n"
			                    + "        }\n\n"
			                    + "        .tick {\n"
			                    + "            text-align: center;\n"
			                    + "            font-size: 36px;\n"
			                    + "            color: green;\n"
			                    + "            margin-bottom: 10px;\n"
			                    + "        }\n\n"
			                    + "        .green {\n"
			                    + "            color: green;\n"
			                    + "            font-size: 1.2en;\n"
			                    + "        }\n\n"
			                    + "        #logo {\n"
			                    + "            font-size: 25px;\n"
			                    + "            font-weight: 600;\n"
			                    + "            font-family: \"Open Sans\", sans-serif;\n"
			                    + "        }\n\n"
			                    + "        .brand {\n"
			                    + "            color: #ff7e0c;\n"
			                    + "            font-family: 'Fredoka', sans-serif;\n"
			                    + "        }\n"
			                    + "    </style>\n"
			                    + "</head>\n\n"
			                    + "<body>\n"
			                    + "    <div class=\"receipt\">\n"
			                    + "        <span id=\"logo\"><span class=\"brand\">C</span>ash<span class=\"brand\">N</span>ex</span>\n"
			                    + "        <h2 style=\"margin: 50px 0;\">Your Transaction was <span class=\"green\">Successful</span></h2>\n\n"
			                    + "        <svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"3 3 16 16\" height=\"150px\" width=\"150px\">\n"
			                    + "            <g transform=\"matrix(1.99997 0 0 1.99997-10.994-2071.68)\" fill=\"#da4453\">\n"
			                    + "                <rect y=\"1037.36\" x=\"7\" height=\"8\" width=\"8\" fill=\"#32c671\" rx=\"4\" />\n"
			                    + "                <path\n"
			                    + "                    d=\"m123.86 12.966l-11.08-11.08c-1.52-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-53.45 53.53-23.953-24.04c-1.521-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-11.08 11.08c-1.521 1.521-2.281 3.368-2.281 5.541 0 2.172.76 4.02 2.281 5.54l29.493 29.493 11.08 11.08c1.52 1.521 3.367 2.281 5.54 2.281 2.172 0 4.02-.761 5.54-2.281l11.08-11.08 58.986-58.986c1.52-1.521 2.281-3.368 2.281-5.541.0001-2.172-.761-4.02-2.281-5.54\"\n"
			                    + "                    fill=\"#fff\" transform=\"matrix(.0436 0 0 .0436 8.177 1039.72)\" stroke=\"none\" stroke-width=\"9.512\" />\n"
			                    + "            </g>\n"
			                    + "        </svg>\n"
			                    + "        <h2>Receipt</h2>\n"
			                    + "        <p>Date: " + latestTransactionDate + "</p>\n"
			                    + "        <p>Time: " + latestTransactionTime + "</p>\n"
			                    + "        <p>Email: "+ senderUserGmail +"</p>\n"
			                    + "        <p>"+ amount + " MMK is deduced from your account</p>\n\n"
			                    + "    </div>\n\n"
			                    + "</body>\n\n"
			                    + "</html>";
;

						String recieverContent = "<html lang=\"en\">\n\n"
			                    + "<head>\n"
			                    + "    <meta charset=\"UTF-8\">\n"
			                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
			                    + "    <title>Receipt</title>\n"
			                    + "    <style>\n"
			                    + "        body {\n"
			                    + "            font-family: Arial, sans-serif;\n"
			                    + "            font-weight: 1.2em;\n"
			                    + "        }\n\n"
			                    + "        .receipt {\n"
			                    + "            width: 100%;\n"
			                    + "            max-width: 500px;\n"
			                    + "            text-align: center;\n"
			                    + "            margin: 20px auto;\n"
			                    + "            padding: 10px;\n"
			                    + "            border: 1px solid #000;\n"
			                    + "            border-radius: 10px;\n"
			                    + "        }\n\n"
			                    + "        .tick {\n"
			                    + "            text-align: center;\n"
			                    + "            font-size: 36px;\n"
			                    + "            color: green;\n"
			                    + "            margin-bottom: 10px;\n"
			                    + "        }\n\n"
			                    + "        .green {\n"
			                    + "            color: green;\n"
			                    + "            font-size: 1.2en;\n"
			                    + "        }\n\n"
			                    + "        .brand-color {\n"
			                    + "            color: #ff7e0c;\n"			             
			                    + "        }\n\n"
			                    + "        #logo {\n"
			                    + "            font-size: 25px;\n"
			                    + "            font-weight: 600;\n"
			                    + "            font-family: \"Open Sans\", sans-serif;\n"
			                    + "        }\n\n"
			                    + "        .brand {\n"
			                    + "            color: #ff7e0c;\n"
			                    + "            font-family: 'Fredoka', sans-serif;\n"
			                    + "        }\n"
			                    + "    </style>\n"
			                    + "</head>\n\n"
			                    + "<body>\n"
			                    + "    <div class=\"receipt\">\n"
			                    + "        <span id=\"logo\"><span class=\"brand\">C</span>ash<span class=\"brand\">N</span>ex</span>\n"
			                    + "        <h2 style=\"margin: 50px 0;\">You've received <span class=\"brand-color\">" + amount +" </span>MMK</h2>\n\n"
			                    + "        <svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"3 3 16 16\" height=\"150px\" width=\"150px\">\n"
			                    + "            <g transform=\"matrix(1.99997 0 0 1.99997-10.994-2071.68)\" fill=\"#da4453\">\n"
			                    + "                <rect y=\"1037.36\" x=\"7\" height=\"8\" width=\"8\" fill=\"#32c671\" rx=\"4\" />\n"
			                    + "                <path\n"
			                    + "                    d=\"m123.86 12.966l-11.08-11.08c-1.52-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-53.45 53.53-23.953-24.04c-1.521-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-11.08 11.08c-1.521 1.521-2.281 3.368-2.281 5.541 0 2.172.76 4.02 2.281 5.54l29.493 29.493 11.08 11.08c1.52 1.521 3.367 2.281 5.54 2.281 2.172 0 4.02-.761 5.54-2.281l11.08-11.08 58.986-58.986c1.52-1.521 2.281-3.368 2.281-5.541.0001-2.172-.761-4.02-2.281-5.54\"\n"
			                    + "                    fill=\"#fff\" transform=\"matrix(.0436 0 0 .0436 8.177 1039.72)\" stroke=\"none\" stroke-width=\"9.512\" />\n"
			                    + "            </g>\n"
			                    + "        </svg>\n"
			                    + "        <h2>Receipt</h2>\n"
			                    + "        <p>Date: " + latestTransactionDate + "</p>\n"
			                    + "        <p>Time: " + latestTransactionTime + "</p>\n"
			                    + "        <p>Received From : "+ senderUserGmail +"</p>\n"
			                    + "		   <p>Your New Balance is : " + recipientBalance + "</p>\n\n"
			                    + "    </div>\n\n"
			                    + "</body>\n\n"
			                    + "</html>";

						mail.sendEmail(senderUserGmail, "Transaction Receipt", senderContent);
						mail.sendEmail(recieverUserGmail, "Transaction Receipt", recieverContent);

						System.out.println("Sender Email : " + senderUserGmail);
						System.out.println("Reciever Email : " + recieverUserGmail);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("The connnection error occured.");
						e.printStackTrace();
					}

					// Redirect the user back to the userDashboard with a success message
					response.sendRedirect(
							request.getContextPath() + "/UserDashboardController");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					response.getWriter().println("An error occurred during the transaction.");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/views/accountNotFoundError.jsp");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/views/accountNotFoundError.jsp");

		}

		// Just a Test
		// Method to retrieve user balance from the database

	}
}

//TransferServlet