package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cashnex.dao.LoanDao;
import com.cashnex.dao.UserDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLoanTableController
 */
public class AdminLoanTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao userDao;
	LoanDao loanDao;

	public AdminLoanTableController() {
		super();
		loanDao = new LoanDao();
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * // Retrieve attributes from loan.jsp loan request double amount = (Double)
		 * request.getAttribute("amount"); String reason = (String)
		 * request.getAttribute("reason"); String estimatedDate = (String)
		 * request.getAttribute("estimatedDate");
		 */

		String action = request.getParameter("action");
		if (action == null) {
			action = "LIST";
		}
		switch (action) {

		case "LIST":
			try {
				listLoanHistory(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;

		case "APPROVE":
			try {
				approveLoanRequest(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;

		case "DENY":
			try {
				deleteLoanRequest(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				listLoanHistory(request, response);
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}

	}

	private void approveLoanRequest(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		double loan_amount = Double.parseDouble(request.getParameter("loan_amount"));
		
		if ( loanDao.approveLoanRequest(id)) {
			userDao.updateUserBalance(id, loan_amount);
			System.out.println("Loan Request was Approved and Insertion to loan_table was completed");
		}
		listLoanHistory(request, response);
		/*
		 * int id = Integer.parseInt(request.getParameter("id"));
		 * 
		 * // Get the loan details Loan loan = loanDao.getLoanById(id); double amount =
		 * loan.getLoanAmount(); int userId = loan.getUserId();
		 * 
		 * // Update user's balance UserDao userDao = new UserDao(); User user =
		 * userDao.getUserByUserId(userId); double currentBalance = user.getBalance();
		 * double updatedBalance = currentBalance + amount;
		 * user.setBalance(updatedBalance);
		 * 
		 * // Update user's balance in the database if (userDao.updateUserBalance(user))
		 * { System.out.println("User's balance updated successfully"); } else {
		 * System.out.println("Failed to update user's balance"); // Handle the failure
		 * scenario, maybe redirect to an error page }
		 * 
		 * // Approve the loan request if (loanDao.approveLoanRequest(id)) { System.out.
		 * println("Loan Request was Approved and Insertion to loan_table was completed"
		 * ); }
		 * 
		 * // Redirect to the loan history page listLoanHistory(request, response);
		 */

	}

	private void deleteLoanRequest(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));

		if (loanDao.deleteLoanRequest(id)) {
			System.out.println("Deleted Loan Request from User ID : " + id);
			request.setAttribute("MSG", "Successfully Deleted");
		}

		listLoanHistory(request, response);
	}

	private void listLoanHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		try {
			List<Map<String, Object>> pendingLoans = loanDao.getPendingLoanRequests();

			request.setAttribute("pendingLoans", pendingLoans);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminLoanTable.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			response.sendRedirect(request.getContextPath() + "/views/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
