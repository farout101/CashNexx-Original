package com.cashnex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class balanceTransferDao {

	public List<Map<String, Object>> getTransactionHistory(String userGmail) throws ClassNotFoundException, SQLException {
	    List<Map<String, Object>> transactionHistory = new ArrayList<>();
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    System.out.println("Start of get Transaction Histoyr method");
	    
	    try {
	        con = DBUtility.getConnection();
	        
	        String query = "SELECT " +
	                            "t.transaction_id, " +
	                            "u.username, " +
	                            "u.gmail, " +
	                            "u.accountNumber, " +
	                            "t.amount, " +
	                            "t.transaction_date, " +
	                            "t.transaction_time " +
	                        "FROM " +
	                            "transaction_table t " +
	                        "JOIN " +
	                            "usertable u ON t.recipient_account_number = u.accountNumber " +
	                        "WHERE " +
	                            "t.sender_user_gmail = ? " +
	                        "ORDER BY " +
	                            "t.transaction_date DESC, t.transaction_time DESC";
	        
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, userGmail);
	        
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Map<String, Object> transaction = new HashMap<>();
	            transaction.put("transaction_id", rs.getInt("transaction_id"));
	            transaction.put("username", rs.getString("username"));
	            transaction.put("gmail", rs.getString("gmail"));
	            transaction.put("accountNumber", rs.getString("accountNumber"));
	            transaction.put("amount", rs.getDouble("amount"));
	            transaction.put("transaction_date", rs.getDate("transaction_date"));
	            transaction.put("transaction_time", rs.getTime("transaction_time"));
	            
	            transactionHistory.add(transaction);
	            
	         // Print the transaction history
				/*
				 * for (Map<String, Object> trans : transactionHistory) {
				 * System.out.println("Transaction ID: " + trans.get("transaction_id"));
				 * System.out.println("Username: " + trans.get("username"));
				 * System.out.println("Gmail: " + trans.get("gmail"));
				 * System.out.println("Account Number: " + trans.get("accountNumber"));
				 * System.out.println("Amount: " + trans.get("amount"));
				 * System.out.println("Transaction Date: " + trans.get("transaction_date"));
				 * System.out.println("Transaction Time: " + trans.get("transaction_time"));
				 * System.out.println("--------------------------------------"); }
				 */
	        }
	    } finally {
	    	System.out.println("Transcation history dao was called");
	    }
	    
	    return transactionHistory;
	}

	
	public double getUserBalance(String userEmail) throws SQLException, ClassNotFoundException {
	    double balance = 0.0;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	      con = DBUtility.getConnection();

	      String sql = "SELECT balance FROM userTable WHERE gmail = ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, userEmail);

	      rs = pstmt.executeQuery();
	      System.out.println("Before rs.next");
	      System.out.println("Emial:" + userEmail);
	      if (rs.next()) {
	        balance = rs.getDouble("balance");
	        System.out.println("DAOSenderBalance:" + balance);
	      }
	    } finally {
	      
	      if (rs != null) {
	        rs.close();
	      }
	      if (pstmt != null) {
	        pstmt.close();
	      }
	      if (con != null) {
	        con.close();
	      }
	    }

	    return balance;
	  }
	
	public double getUserBalancebyAccNum(String accNum) throws SQLException, ClassNotFoundException {
	    double balance = 0.0;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	      con = DBUtility.getConnection();

	      String sql = "SELECT balance FROM userTable WHERE accountNumber = ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, accNum);

	      rs = pstmt.executeQuery();
	      System.out.println("Before rs.next");
	      System.out.println("Emial:" + accNum);
	      if (rs.next()) {
	        balance = rs.getDouble("balance");
	        System.out.println("DAOSenderBalance:" + balance);
	      }
	    } finally {
	      
	      if (rs != null) {
	        rs.close();
	      }
	      if (pstmt != null) {
	        pstmt.close();
	      }
	      if (con != null) {
	        con.close();
	      }
	    }

	    return balance;
	  }
//		
//	public boolean userExists(String accNum) throws SQLException, ClassNotFoundException {
//	    boolean balance = true;
//	    Connection con = null;
//	    PreparedStatement pstmt = null;
//	    ResultSet rs = null;
//	    
//	    try {
//	      con = DBUtility.getConnection();
//
//	      String sql = "SELECT balance FROM userTable WHERE accountNumber = ?";
//	      pstmt = con.prepareStatement(sql);
//	      pstmt.setString(1, accNum);
//
//	      rs = pstmt.executeQuery();
//	      System.out.println("Before rs.next");
//	      System.out.println("Emial:" + accNum);
//	      if (rs.next()) {
//	        balance = rs.getDouble("balance");
//	        System.out.println("DAOSenderBalance:" + balance);
//	      }
//	    } finally {
//	      
//	      if (rs != null) {
//	        rs.close();
//	      }
//	      if (pstmt != null) {
//	        pstmt.close();
//	      }
//	      if (con != null) {
//	        con.close();
//	      }
//	    }
//
//	    return balance;
//	  }
//		
	
	
	  // Method to update user balance in the database
	  public void updateBalance(String userGamil, double newBalance) throws SQLException, ClassNotFoundException {
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	      
	      con = DBUtility.getConnection();

	      String sql = "UPDATE userTable SET balance = ? WHERE gmail = ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setDouble(1, newBalance);
	      pstmt.setString(2, userGamil);

	      int rowsAffected = pstmt.executeUpdate();
	      
	   // Check if any rows were affected by the update operation
	      if (rowsAffected > 0) {
	        System.out.println("Balance updated successfully for user: " + userGamil);
	      } else {
	        System.out.println("No user found with username: " + userGamil);
	      }
	    } finally {

	      if (pstmt != null) {
	        pstmt.close();
	      }
	      if (con != null) {
	        con.close();
	      }
	    }
	  }
	  
	  public void updateBalancebyAccNum(String accNum, double newBalance) throws SQLException, ClassNotFoundException {
		    Connection con = null;
		    PreparedStatement pstmt = null;

		    try {
		      
		      con = DBUtility.getConnection();

		      String sql = "UPDATE userTable SET balance = ? WHERE accountNumber = ?";
		      pstmt = con.prepareStatement(sql);
		      pstmt.setDouble(1, newBalance);
		      pstmt.setString(2, accNum);

		      int rowsAffected = pstmt.executeUpdate();
		      
		   // Check if any rows were affected by the update operation
		      if (rowsAffected > 0) {
		        System.out.println("Balance updated successfully for user: " + accNum);
		      } else {
		        System.out.println("No user found with username: " + accNum);
		      }
		    } finally {

		      if (pstmt != null) {
		        pstmt.close();
		      }
		      if (con != null) {
		        con.close();
		      }
		    }
		  }
	
	  public void updatetransaction(String senderGmail, String receiverGmail, double amount) throws SQLException, ClassNotFoundException {
	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {
	            con = DBUtility.getConnection();

	            String sql = "INSERT INTO transaction (senderGmail, receiverGmail, amount, type) VALUES (?, ?, ?, ?)";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, senderGmail);
	            pstmt.setString(2, receiverGmail);
	            pstmt.setDouble(3, amount);
	            pstmt.setString(4, "Regular");
	            
	            int rowsAffected = pstmt.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Transaction updated successfully.");
	            } else {
	                System.out.println("Failed to update transaction.");
	            }
	        } finally {
	            // Close resources in a finally block to ensure they're always closed
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        }
	    }

	  public void recordTransaction(String senderUserGmail, String recipientAccNum, double amount)
		        throws ClassNotFoundException, SQLException {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    try {
		        con = DBUtility.getConnection();
		        String insertQuery = "INSERT INTO transaction_table (sender_user_gmail, recipient_account_number, amount, transaction_date, transaction_time) VALUES (?, ?, ?, ?, ?)";
		        pstmt = con.prepareStatement(insertQuery);
		        pstmt.setString(1, senderUserGmail);
		        pstmt.setString(2, recipientAccNum);
		        pstmt.setDouble(3, amount);
		        
		        // Set both date and time components
		        LocalDateTime now = LocalDateTime.now();
		        pstmt.setObject(4, now.toLocalDate()); // Set date
		        pstmt.setObject(5, now.toLocalTime()); // Set time
		        
		        pstmt.executeUpdate();
		    } finally {
		        System.out.println("Transaction was Recorded");
		    }
		}

	  
	  }
