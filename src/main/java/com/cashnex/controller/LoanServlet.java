package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cashnex.dao.LoanDao;
import com.cashnex.dao.UserDao;
import com.cashnex.model.Loan;
import com.cashnex.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loan")
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LoanDao loanDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        loanDao = new LoanDao();
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 
    	
    	// To show Loan History
    	loanDao = new LoanDao();
    	User user = null;
    	try {
			user = userDao.getUserByUserId(Integer.parseInt(request.getParameter("userId")));
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
             List<Loan> loans = loanDao.getLoanDetails();

             
             double totalRepaymentAmount = 0;
             for (Loan loan : loans) {
                 totalRepaymentAmount += loan.getRepayment_amount();
             }

             // Set the total repayment amount as an attribute in the request
             request.setAttribute("totalRepaymentAmount", totalRepaymentAmount);
             request.setAttribute("loans", loans);
             request.setAttribute("user", user);
             	
             request.getRequestDispatcher("/WEB-INF/views/loan.jsp").forward(request, response);
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
             response.sendRedirect(request.getContextPath() + "/error.jsp");
         }
     
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get loan application data from request parameters
		/*
		 * String applicantName = request.getParameter("applicantName"); String
		 * applicantEmail = request.getParameter("applicantEmail"); double loanAmount =
		 * Double.parseDouble(request.getParameter("loanAmount"));
		 * 
		 * try { // Process loan application boolean applicationApproved =
		 * loanDao.processLoanApplication(applicantName, applicantEmail, loanAmount);
		 * 
		 * if (applicationApproved) { // If loan application is approved
		 * response.getWriter().
		 * println("Congratulations! Your loan application has been approved."); } else
		 * { // If loan application is rejected response.getWriter().
		 * println("Sorry, your loan application has been rejected."); } } catch
		 * (SQLException | ClassNotFoundException e) { // Handle database errors or
		 * class loading errors e.printStackTrace(); response.getWriter().
		 * println("An error occurred while processing your loan application."); }
		 */
    }
}
