package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.cashnex.dao.LoanDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoanAdminController
 */
public class LoanAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	LoanDao loanDao ;

    public LoanAdminController() {
        super();
        loanDao = new LoanDao();
    }

  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Double amount = Double.parseDouble(request.getParameter("amount"));
    	String reason = request.getParameter("reason");
    	String estimatedDate = request.getParameter("date");
    	String username = request.getParameter("username");
    	String accountNumber = request.getParameter("accountNumber");
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	
        request.setAttribute("amount", amount);
        request.setAttribute("reason", reason);
        request.setAttribute("estimatedDate", estimatedDate);
        request.setAttribute("userId", user_id);
        
        try {
			loanDao.insertIntoPendingLoanTable(user_id,username,amount,reason,estimatedDate, accountNumber);
			  request.setAttribute("loanApplicationSuccess", true);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.sendRedirect(request.getContextPath() + "/views/loan.jsp");

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
