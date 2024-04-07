package com.cashnex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.cashnex.model.Loan;

public class LoanDao {

	public boolean updateLoanFullyPaid(int loanId, boolean fullyPaid) throws ClassNotFoundException {
	    boolean updated = false;
	    System.out.println("Update Loan Fully Paid was called");
	    try {
	        Connection conn = DBUtility.getConnection();// Get connection to database
	        String query = "UPDATE loan_table SET fully_paid = ? WHERE loan_id = ?";
	        PreparedStatement preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setBoolean(1, fullyPaid);
	        preparedStatement.setInt(2, loanId);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            updated = true;
	    	    System.out.println("Update Loan Fully Paid was completed");

	        }
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	   
	    return updated;
	}

	
	public boolean updateLoan(int loanId, double paidAmount, double repaymentAmount)
			throws ClassNotFoundException, SQLException {
		String query = "UPDATE loan_table SET paid_amount = ?, repayment_amount = ? WHERE loan_id = ?";

		try (Connection conn = DBUtility.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setDouble(1, paidAmount);
			stmt.setDouble(2, repaymentAmount);
			stmt.setInt(3, loanId);

			int rowsAffected = stmt.executeUpdate();

			return rowsAffected > 0;
		}
	}

	public Loan getLoanById(int loanId) throws ClassNotFoundException, SQLException {
		Loan loan = null;
		String query = "SELECT * FROM loan_table WHERE loan_id = ?";

		System.out.println("Loan id is " + loanId);

		try (Connection conn = DBUtility.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, loanId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setUserId(rs.getInt("user_id"));
				loan.setAccountNumber(rs.getString("account_number"));
				loan.setReason(rs.getString("reason"));
				loan.setLoanAmount(rs.getDouble("loan_amount"));
				loan.setPaidAmount(rs.getDouble("paid_amount"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setFullyPaid(rs.getBoolean("fully_paid"));
				loan.setRepayment_amount(rs.getDouble("repayment_amount"));
			}
		}

		return loan;
	}

	public boolean updateLoan(int loanId, String reason, double paidAmount, double leftAmount)
			throws ClassNotFoundException {
		String sql = "UPDATE loan_table SET reason = ?, paid_amount = ?, left_amount = ? WHERE id = ?";
		try (Connection conn = DBUtility.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, reason);
			statement.setDouble(2, paidAmount);
			statement.setDouble(3, leftAmount);
			statement.setInt(4, loanId);

			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Loan> getLoansByAccountNumber(String accountNumber) throws SQLException, ClassNotFoundException {
		List<Loan> loans = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtility.getConnection();
			String query = "SELECT * FROM loan_table WHERE account_number = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, accountNumber);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Loan loan = new Loan();
				loan.setLoanId(resultSet.getInt("loan_id"));
				loan.setAccountNumber(resultSet.getString("account_number"));
				loan.setLoanAmount(resultSet.getDouble("loan_amount"));
				loans.add(loan);
			}
		} finally {
			System.out.println("Get Loans By Account number was called");
		}
		return loans;
	}

	public List<Map<String, Object>> getPendingLoanRequests() throws ClassNotFoundException, SQLException {
		List<Map<String, Object>> pendingLoans = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// Establish a database connection
			connection = DBUtility.getConnection();

			// SQL query to select pending loan requests
			String sql = "SELECT * FROM pendingloaninfo";

			// Create a PreparedStatement
			statement = connection.prepareStatement(sql);

			// Execute the query
			resultSet = statement.executeQuery();

			// Process the ResultSet
			while (resultSet.next()) {
				// Create a map to store the column names and values for each row
				Map<String, Object> loanData = new HashMap<>();

				// Retrieve data from the ResultSet and add it to the map
				loanData.put("user_id", resultSet.getInt("user_id"));
				loanData.put("username", resultSet.getString("username"));
				loanData.put("loan_amount", resultSet.getDouble("loan_amount"));
				loanData.put("reason", resultSet.getString("reason"));
				loanData.put("estimated_date", resultSet.getString("estimated_date"));
				loanData.put("accountNumber", resultSet.getString("accountNumber"));

				// Add the map to the list
				pendingLoans.add(loanData);
			}
		} finally {
			System.out.println("Retrieving from Pending Loan Table was completed");
		}

		return pendingLoans;
	}

	public List<Loan> getLoanDetails() throws ClassNotFoundException, SQLException {
		List<Loan> loans = new ArrayList<>();

		Connection con = DBUtility.getConnection();
		if (con == null) {
			return loans;
		}

		// SQL query to retrieve user name and loan details
		String sql = "SELECT u.username, l.account_number, l.loan_id, l.reason, l.loan_amount, l.paid_amount, l.due_date, l.fully_paid, l.repayment_amount "
				+ "FROM loan_table l " + "JOIN usertable u ON l.user_id = u.userId";

		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Loan loan = new Loan();

				loan.setUserName(rs.getString("username"));
				loan.setAccountNumber(rs.getString("account_number"));

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setReason(rs.getString("reason"));
				loan.setLoanAmount(rs.getDouble("loan_amount"));
				loan.setPaidAmount(rs.getDouble("paid_amount"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setFullyPaid(rs.getBoolean("fully_paid"));
				loan.setRepayment_amount(rs.getDouble("repayment_amount"));

				loans.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return loans;
	}

	public List<Loan> getLoanID() throws ClassNotFoundException, SQLException {
		List<Loan> loans = new ArrayList<>();

		Connection con = DBUtility.getConnection();
		if (con == null) {
			return loans;
		}

		// SQL query to retrieve user name and loan details
		String sql = "SELECT u.username, l.account_number, l.loan_id, l.reason, l.loan_amount, l.paid_amount, l.due_date, l.fully_paid, l.repayment_amount "
				+ "FROM loan_table l " + "JOIN usertable u ON l.user_id = u.userId";

		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Loan loan = new Loan();

				loan.setUserName(rs.getString("username"));
				loan.setAccountNumber(rs.getString("account_number"));

				loan.setLoanId(rs.getInt("loan_id"));
				loan.setReason(rs.getString("reason"));
				loan.setLoanAmount(rs.getDouble("loan_amount"));
				loan.setPaidAmount(rs.getDouble("paid_amount"));
				loan.setDueDate(rs.getDate("due_date"));
				loan.setFullyPaid(rs.getBoolean("fully_paid"));
				loan.setRepayment_amount(rs.getDouble("repayment_amount"));

				loans.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return loans;
	}

	public boolean deleteLoanInfo(int user_id) throws ClassNotFoundException, SQLException {

		String SQL = "delete from loan_table where user_id = ?";
		boolean flag = false;
		Connection con = DBUtility.getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL);

		pstmt.setInt(1, user_id);

		int rowDeleted = pstmt.executeUpdate();

		if (rowDeleted > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean deleteLoanRequest(int user_id) throws ClassNotFoundException, SQLException {

		String SQL = "delete from pendingloaninfo where user_id = ?";
		boolean flag = false;
		Connection con = DBUtility.getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL);

		pstmt.setInt(1, user_id);

		int rowDeleted = pstmt.executeUpdate();

		if (rowDeleted > 0) {
			flag = true;
		}
		return flag;
	}

	public void insertIntoPendingLoanTable(int user_id, String username, Double amount, String reason,
			String estimatedDate, String accountNumber) throws ClassNotFoundException, SQLException, ParseException {

		String SQL = "INSERT INTO pendingloaninfo (user_id,username, loan_amount, reason, estimated_date, accountNumber) VALUES (?,?,?,?,?,?)";

		Connection con = DBUtility.getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL);

		pstmt.setInt(1, user_id);
		pstmt.setString(2, username);
		pstmt.setDouble(3, amount);
		pstmt.setString(4, reason);

		// Parse the string date into a java.sql.Date object
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		java.util.Date utilDate = sdf.parse(estimatedDate);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		pstmt.setDate(5, sqlDate);
		pstmt.setString(6, accountNumber);

		pstmt.executeUpdate();

	}

	public boolean approveLoanRequest(int id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmtSelect = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;

		try {
			// Retrieve pending loan details by id
			con = DBUtility.getConnection();
			String selectQuery = "SELECT * FROM pendingloaninfo WHERE user_id = ?";
			pstmtSelect = con.prepareStatement(selectQuery);
			pstmtSelect.setInt(1, id);
			rs = pstmtSelect.executeQuery();

			if (rs.next()) {
				// Calculate repayment amount based on estimated repayment date and loan amount
				LocalDate today = LocalDate.now();
				LocalDate estimatedDate = rs.getDate("estimated_date").toLocalDate();
				long daysDifference = ChronoUnit.DAYS.between(today, estimatedDate);

				if (daysDifference < 30) {
					daysDifference = 30; // Set daysDifference to 30 if it's below 5
				}

				double loanAmount = rs.getDouble("loan_amount");
				double repaymentAmount = Math.ceil(loanAmount * (1 + (0.05 * (daysDifference / 30.0)))); // Round up repaymentAmount

				// Insert pending loan details into loan_table with calculated repayment amount
				String insertQuery = "INSERT INTO loan_table (user_id, account_number, reason, loan_amount, due_date, repayment_amount, fully_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";
				pstmtInsert = con.prepareStatement(insertQuery);
				pstmtInsert.setInt(1, rs.getInt("user_id"));
				pstmtInsert.setString(2, rs.getString("accountNumber"));
				pstmtInsert.setString(3, rs.getString("reason"));
				pstmtInsert.setDouble(4, loanAmount);
				pstmtInsert.setDate(5, rs.getDate("estimated_date"));
				pstmtInsert.setDouble(6, repaymentAmount);
				pstmtInsert.setBoolean(7, false); // Assuming initially not fully paid
				int rowsInserted = pstmtInsert.executeUpdate();

				// Delete the pending loan from pendingloaninfo table if insertion is successful
				if (rowsInserted > 0) {
					String deleteQuery = "DELETE FROM pendingloaninfo WHERE user_id = ?";
					pstmtDelete = con.prepareStatement(deleteQuery);
					pstmtDelete.setInt(1, id);
					pstmtDelete.executeUpdate();

					return true;
				}
			}
		} finally {

		}

		return false;

		/*
		 * public boolean approveLoanRequest(int id) throws ClassNotFoundException,
		 * SQLException { Connection con = null; PreparedStatement pstmtSelect = null;
		 * PreparedStatement pstmtInsert = null; PreparedStatement pstmtDelete = null;
		 * ResultSet rs = null;
		 * 
		 * try { // Retrieve pending loan details by id con = DBUtility.getConnection();
		 * String selectQuery = "SELECT * FROM pendingloaninfo WHERE user_id = ?";
		 * pstmtSelect = con.prepareStatement(selectQuery); pstmtSelect.setInt(1, id);
		 * rs = pstmtSelect.executeQuery();
		 * 
		 * if (rs.next()) { // Insert pending loan details into loan_table String
		 * insertQuery =
		 * "INSERT INTO loan_table (user_id, account_number, reason, loan_amount," +
		 * "paid_amount, due_date, fully_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";
		 * 
		 * pstmtInsert = con.prepareStatement(insertQuery); pstmtInsert.setInt(1,
		 * rs.getInt("user_id")); pstmtInsert.setDouble(2,
		 * rs.getDouble("accountNumber")); pstmtInsert.setString(3,
		 * rs.getString("reason")); pstmtInsert.setDouble(4,
		 * rs.getDouble("loan_amount")); pstmtInsert.setDouble(5,
		 * rs.getDouble("paid_amount")); pstmtInsert.setString(5,
		 * rs.getString("accountNumber")); int rowsInserted =
		 * pstmtInsert.executeUpdate();
		 * 
		 * // Delete the pending loan from pendingloaninfo table if insertion is
		 * successful if (rowsInserted > 0) { String deleteQuery =
		 * "DELETE FROM pendingloaninfo WHERE id = ?"; pstmtDelete =
		 * con.prepareStatement(deleteQuery); pstmtDelete.setInt(1, id);
		 * pstmtDelete.executeUpdate();
		 * 
		 * return true; } } } finally {
		 * 
		 * 
		 * }
		 */

	}

}