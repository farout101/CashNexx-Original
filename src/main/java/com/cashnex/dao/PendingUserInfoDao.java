package com.cashnex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cashnex.model.User;

public class PendingUserInfoDao {

	 public void insertUserData(String userName, String nrcNumber, String userGmail, String career, String userPassword, String accountNumber
		      ) throws SQLException, ClassNotFoundException {

		    Connection con = DBUtility.getConnection();
		    String sql = "INSERT INTO pendingUserInfo (username, nrcNumber, gmail, career, hashedPassword, accountNumber) VALUES (?, ?, ?, ?, ?,?)";

		    PreparedStatement pstmt = con.prepareStatement(sql);

		    pstmt.setString(1, userName);
		    pstmt.setString(2, nrcNumber);
		    pstmt.setString(3, userGmail);
		    pstmt.setString(4, career);
		    pstmt.setString(5, userPassword);
		    pstmt.setString(6, accountNumber);
//		    pstmt.setDouble(6, userBalance);

		    int rowAffected = pstmt.executeUpdate();

		    if (rowAffected > 0) {
		      System.out.println("Niceee");
		    }
		    // Log here to check whether insertion is succeeded or not
		  }

		  public List<User> retrieveUserData(String userName) throws SQLException, ClassNotFoundException {
		    List<User> userDataList = new ArrayList<>();

		    Connection con = DBUtility.getConnection();

		    // Prepare a PreparedStatement to avoid SQL injection
		    PreparedStatement pstmt = con.prepareStatement("SELECT * FROM pendingUserInfo WHERE username = ?");
		    pstmt.setString(1, userName);

		    ResultSet rs = pstmt.executeQuery();

		    while (rs.next()) {
		      // Retrieve data from the result set and create a UserData object
		      User userData = new User();
		      userData.setUsername(rs.getString("username"));
		      userData.setNrcNumber(rs.getString("nrcNumber"));
		      userData.setEmail(rs.getString("gmail"));
		      userData.setCareer(rs.getString("career"));
		      userData.setBalance(rs.getDouble("balance"));
		      userData.setPassword(rs.getString("hashedPassword"));

		      // Add the UserData object to the list
		      userDataList.add(userData);
		    }

		    // Close resources
		    rs.close();
		    pstmt.close();
		    con.close();

		    return userDataList;
		  }
}
