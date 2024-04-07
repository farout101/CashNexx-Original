package com.cashnex.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cashnex.dao.UserDao;
import com.cashnex.dao.balanceTransferDao;
import com.cashnex.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserDashboardController
 */
public class UserDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected UserDao userDao;
    protected balanceTransferDao balanceTransferDao;
    
    public UserDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	userDao = new UserDao();
    	balanceTransferDao = new balanceTransferDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userGmail");
        
       
        // Retrieve user data from the database based on user ID
        User user = null;
		try {
			user = UserDao.getUserByGmail(userEmail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		List<Map<String, Object>> transactionHistory = null;
		try {
			transactionHistory = balanceTransferDao.getTransactionHistory(userEmail);
			System.out.println("Get Transaction History method was called");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Eroor in getTransactionHistory method");
		}
		System.out.println("End of getTransaction Histoyr");
        request.setAttribute("transactionHistory", transactionHistory);
		
        if (user != null) {
            // User data retrieved successfully, forward to dashboard with user data
            request.setAttribute("user", user);
            
			
        } else {
            // User data not found, handle error
            // You can redirect to an error page or display an error message
        }
        request.getRequestDispatcher("/views/userDashboard.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
