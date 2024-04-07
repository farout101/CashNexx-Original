package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.cashnex.dao.LoanDao;
import com.cashnex.dao.UserDao;
import com.cashnex.model.Loan;
import com.cashnex.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PayLoanServlet
 */
public class PayLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoanDao loanDao;
	UserDao userDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayLoanServlet() {
		super();
		loanDao = new LoanDao();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loanIdStr = request.getParameter("loanId");
		int loanId = Integer.parseInt(loanIdStr);
		double paidAmount = Double.parseDouble(request.getParameter("payLoanAmount"));

		System.out.println(loanId);
		
		try {
			// Get the loan record
			Loan loan = loanDao.getLoanById(loanId);
			double currentPaidAmount = loan.getPaidAmount();
			double currentRepaymentAmount = loan.getRepayment_amount();
			double updatedPaidAmount = currentPaidAmount + paidAmount;
			double updatedRepaymentAmount = currentRepaymentAmount - paidAmount;

			// Update loan record
			boolean loanUpdateSuccess = loanDao.updateLoan(loanId, updatedPaidAmount, updatedRepaymentAmount);

			if (loanUpdateSuccess) {
				// Update user's balance
				User user = userDao.getUserByAccountId(loan.getAccountNumber());
				double currentUserBalance = user.getBalance();
				double updatedUserBalance = currentUserBalance - paidAmount;
				boolean userUpdateSuccess = userDao.updateBalance(user.getAccountNumber(), updatedUserBalance);

				if (updatedRepaymentAmount == 0 ) {
					loanDao.updateLoanFullyPaid(loanId,true);
				}
				
				if (userUpdateSuccess) {
					response.sendRedirect(request.getContextPath() + "/UserDashboardController");
				} else {
					System.out.println("UserUpdateSuccess doesnt work");
				}
			} else {
				System.out.println("Loan Update doesnt work");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Everything doesnt work");

		}
	}

}
