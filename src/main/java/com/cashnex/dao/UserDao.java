package com.cashnex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cashnex.model.User;

public class UserDao {
	

	public static List<String> getAccountNumbers() throws SQLException, ClassNotFoundException {
        List<String> accountNumbers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBUtility.getConnection();
            
            String sql = "SELECT accountNumber FROM usertable";
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String email = resultSet.getString("accountNumber");
                accountNumbers.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        
        return accountNumbers;
    }
	
	
	public static String getUserGmailByAccNumber(String accountNumber) throws ClassNotFoundException, SQLException {
		String userGmail = null;
        String query = "SELECT gmail from usertable WHERE accountNumber = ?";
        
        try (Connection con = DBUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                userGmail = rs.getString("gmail");
            }
        }
        
        
       System.out.println("Dao reciever Email : " + userGmail);
       
        return userGmail;
    }
	
	public List<String> getOriginalUserEmails() throws SQLException, ClassNotFoundException {
        List<String> bannedGmails = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBUtility.getConnection();
            
            String sql = "SELECT gmail FROM usertable";
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String email = resultSet.getString("gmail");
                bannedGmails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        
        return bannedGmails;
    }
	
	public boolean changePassword(String userEmail, String newPassword) throws SQLException, ClassNotFoundException {
	    // Define a boolean variable to track the success of the password change operation
	    boolean passwordChanged = false;

	    // Prepare the SQL query to update the password for the user with the given email
	    String sql = "UPDATE userTable SET hashedPassword = ? WHERE gmail = ?";

	    // Try-with-resources to automatically close the resources
	    try (
	    	Connection con = DBUtility.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        // Set the parameters in the PreparedStatement
	        pstmt.setString(1, newPassword);
	        pstmt.setString(2, userEmail);

	        // Execute the update query
	        int rowsUpdated = pstmt.executeUpdate();

	        // If at least one row is updated, it means the password was changed successfully
	        if (rowsUpdated > 0) {
	            passwordChanged = true;
	        }
	    }

	    // Return the boolean value indicating whether the password was changed successfully
	    return passwordChanged;
	}
	
	 public boolean updateBalance(String accountNumber, double newBalance) throws ClassNotFoundException, SQLException {
	        String query = "UPDATE usertable SET balance = ? WHERE accountNumber = ?";
	        
	        try (Connection conn = DBUtility.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setDouble(1, newBalance);
	            stmt.setString(2, accountNumber);
	            
	            int rowsAffected = stmt.executeUpdate();
	            
	            return rowsAffected > 0;
	        }
	    }
	
	 public User getUserByAccountId(String accountNumber) throws ClassNotFoundException, SQLException {
	        User user = null;
	        String query = "SELECT * FROM usertable WHERE accountNumber = ?";
	        
	        try (Connection conn = ConnectionManager.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setString(1, accountNumber);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                user = new User();
	                user.setUserId(rs.getInt("userId"));
	                user.setUsername(rs.getString("username"));
	                user.setNrcNumber(rs.getString("nrcNumber"));
	                user.setEmail(rs.getString("gmail"));
	                user.setCareer(rs.getString("Career"));
	                user.setBalance(rs.getDouble("balance"));
	                user.setPassword(rs.getString("hashedPassword"));
	                user.setAccountNumber(rs.getString("accountNumber"));
	            }
	        }
	        
	        return user;
	    }
	    
	    // Method to get user ID by account ID
	    public int getUserId(long accountNumber) throws ClassNotFoundException, SQLException {
	        int userId = -1;
	        String query = "SELECT userId FROM usertable WHERE accountNumber = ?";
	        
	        try (Connection conn = ConnectionManager.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setLong(1, accountNumber);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                userId = rs.getInt("userId");
	            }
	        }
	        
	        return userId;
	    }
	
	public int getUserCount() throws ClassNotFoundException, SQLException {
		
		int count = -1;
		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS user_count FROM userTable"); 
		
		if(rs.next()) {
			count = rs.getInt("user_count");
		}
		return count;
	}
	
	
	public User getUserByUserId(int userId) throws SQLException, ClassNotFoundException {
        User user = null;
        
        try (Connection con = DBUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM userTable WHERE userId = ?")) {
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {

            	if (rs.next()) {

                	user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setUsername(rs.getString("userName"));
                    user.setNrcNumber(rs.getString("nrcNumber"));
                    user.setEmail(rs.getString("gmail"));
                    user.setCareer(rs.getString("Career"));
                    user.setBalance(rs.getDouble("balance"));
                    user.setPassword(rs.getString("hashedPassword"));
                    user.setAccountNumber(rs.getString("accountNumber"));
            	}
            }
        }
        
        return user;
    }
	
	public int getUserIdByEmailAndPassword(String userEmail, String userPassword) throws SQLException, ClassNotFoundException {
		
		Connection con = DBUtility.getConnection();
        int userId = -1;
        String query = "SELECT userId FROM userTable WHERE gmail = ? AND hashedPassword = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, userEmail);
            statement.setString(2, userPassword);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("userId");
                }
            }
        }
        
        return userId;
    }

	public List<User> getUserList() throws ClassNotFoundException, SQLException {

		List<User> list = new ArrayList<User>();
		User user = null;

		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM userTable");

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUsername(rs.getString("username"));
			user.setNrcNumber(rs.getString("nrcNumber"));
			user.setEmail(rs.getString("gmail"));
			user.setCareer(rs.getString("Career"));
			user.setBalance(rs.getDouble("balance"));
			user.setPassword(rs.getString("hashedPassword"));
            user.setAccountNumber(rs.getString("accountNumber"));

			list.add(user);
		}

		return list;

	}

	public List<String> checkUsernames() throws SQLException, ClassNotFoundException {
		List<String> usernames = new ArrayList<>();
		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT userName FROM userTable");

		while (rs.next()) {
			usernames.add(rs.getString("userName"));
		}

		rs.close();
		stmt.close();
		con.close();

		return usernames;
	}
	public List<String> checkUserGmails() throws SQLException, ClassNotFoundException {
		List<String> usernames = new ArrayList<>();
		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT gmail FROM userTable");
		
		while (rs.next()) {
			usernames.add(rs.getString("gmail"));
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return usernames;
	}

	public List<String> checkPasswords() throws SQLException, ClassNotFoundException {
		List<String> passwords = new ArrayList<>();
		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT hashedPassword FROM userTable");

		while (rs.next()) {
			passwords.add(rs.getString("hashedPassword"));
		}

		rs.close();
		stmt.close();
		con.close();

		return passwords;
	}

	public void insertUserData(String userName, String nrcNumber, String userGmail, String career, String userPassword, String accountNumber
			) throws SQLException, ClassNotFoundException {

		Connection con = DBUtility.getConnection();
		String sql = "INSERT INTO usertable (username, nrcNumber, gmail, career, hashedPassword, accountNumber)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		// need to hash

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, userName);
		pstmt.setString(2, nrcNumber);
		pstmt.setString(3, userGmail);
		pstmt.setString(4, career);
		pstmt.setString(5, userPassword);
		pstmt.setString(6, accountNumber);
//		pstmt.setDouble(6, userBalance);

		int rowAffected = pstmt.executeUpdate();

		// Log here to check whether insertion is succeeded or not
	}

	public static User getUserById(int id) throws SQLException, ClassNotFoundException {

		User user = null;
		   
			user = new User();
			String sql = "SELECT * FROM userTable where id="+id;
			Connection connection = DBUtility.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				user.setUserId(resultSet.getInt("userId"));
				user.setUsername(resultSet.getString("username"));
				user.setNrcNumber(resultSet.getString("nrcNumber"));
				user.setEmail(resultSet.getString("gmail"));
				user.setCareer(resultSet.getString("Career"));
				user.setBalance(resultSet.getDouble("balance"));
				user.setPassword(resultSet.getString("hashedPassword"));
                user.setAccountNumber(resultSet.getString("accountNumber"));

				}
			
		    return user;

	}
	
	public static User getUserByGmail(String gmail) throws SQLException, ClassNotFoundException {
	    User user = null;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        // Create a connection
	        connection = DBUtility.getConnection();

	        // Prepare the SQL statement with a parameterized query
	        String sql = "SELECT * FROM usertable WHERE gmail = ?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, gmail);

	        // Execute the query
	        resultSet = preparedStatement.executeQuery();

	        // Process the result set
	        if (resultSet.next()) {
	            user = new User();
	            user.setUserId(resultSet.getInt("userId"));
	            user.setUsername(resultSet.getString("username"));
	            user.setNrcNumber(resultSet.getString("nrcNumber"));
	            user.setEmail(resultSet.getString("gmail"));
	            user.setCareer(resultSet.getString("Career"));
	            user.setBalance(resultSet.getDouble("balance"));
	            user.setPassword(resultSet.getString("hashedPassword"));
	            user.setAccountNumber(resultSet.getString("accountNumber"));
	        }
	    } finally {
	        // Close resources in a finally block
	        System.out.println("get User  BY Gmail was called");
	    }

	    return user;
	}


	/*
	 * public boolean delete(int id) throws ClassNotFoundException, SQLException {
	 * boolean flag = false;
	 * 
	 * String sql = "DELETE FROM usertable where id=" + id; Connection con =
	 * DBUtility.getConnection(); PreparedStatement preparedStatement =
	 * con.prepareStatement(sql); int rowDeleted =
	 * preparedStatement.executeUpdate(); if (rowDeleted > 0) flag = true;
	 * 
	 * return flag; }
	 */

	// Insert Data using User Object
	// Below methods are not updated
	public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
		boolean flag = false;

		String sql = "INSERT INTO usertable (username, nrcNumber, userGmail, career, hashedPassword, balance) VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = DBUtility.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getNrcNumber());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getCareer());
		pstmt.setString(5, user.getPassword());
		pstmt.setDouble(6, user.getBalance());
		
		int rowInserted = pstmt.executeUpdate();

		return flag;
	}

	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Phyo Zaw Linn
	public List<User> getBanUserList() throws ClassNotFoundException, SQLException {

		List<User> list = new ArrayList<User>();
		User user = null;

		Connection con = DBUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM banned_user_table");

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUsername(rs.getString("username"));
			user.setNrcNumber(rs.getString("nrcNumber"));
			user.setEmail(rs.getString("gmail"));
			user.setCareer(rs.getString("Career"));
			user.setBalance(rs.getDouble("balance"));
			user.setPassword(rs.getString("hashedPassword"));
			user.setAccountNumber(rs.getString("accountNumber"));

			list.add(user);
		}

		return list;

	}
	
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		boolean flag = false;

		String sql = "DELETE FROM banned_user_table where userid=" + id;
		Connection con = DBUtility.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		int rowDeleted = preparedStatement.executeUpdate();
		if (rowDeleted > 0)
			flag = true;

		return flag;
	}

	public boolean ban(int id) throws ClassNotFoundException, SQLException {

	    Connection con = DBUtility.getConnection();

	    User user = null;
	    boolean banSuccessful = false;

	    user = new User();
	    String sql = "SELECT * FROM usertable where userId=" + id;
	    Connection connection = DBUtility.getConnection();
	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);
	    if (resultSet.next()) {
	        user.setUserId(resultSet.getInt("userId"));
	        user.setUsername(resultSet.getString("username"));
	        user.setNrcNumber(resultSet.getString("nrcNumber"));
	        user.setEmail(resultSet.getString("gmail"));
	        user.setCareer(resultSet.getString("Career"));
	        user.setBalance(resultSet.getDouble("balance"));
	        user.setPassword(resultSet.getString("hashedPassword"));
	        user.setAccountNumber(resultSet.getString("accountNumber"));
	    }

	    String dsql = "DELETE FROM usertable where userId=" + id;
	    PreparedStatement deleteStatement = con.prepareStatement(dsql);
	    int rowDeleted = deleteStatement.executeUpdate();
	    
	    if (rowDeleted > 0) {
	        String insql = "INSERT INTO banned_user_table (username, nrcNumber, gmail, career, balance, hashedPassword, accountNumber)"
	                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement insertStatement = con.prepareStatement(insql);
	        insertStatement.setString(1, user.getUsername());
	        insertStatement.setString(2, user.getNrcNumber());
	        insertStatement.setString(3, user.getEmail());
	        insertStatement.setString(4, user.getCareer());
	        insertStatement.setDouble(5, user.getBalance());
	        insertStatement.setString(6, user.getPassword());
	        insertStatement.setString(7, user.getAccountNumber());    
	        int rowsInserted = insertStatement.executeUpdate();
	        if (rowsInserted > 0) {
	            banSuccessful = true;
	        }
	    }

	    return banSuccessful;
	}

	public boolean unban(int id) throws ClassNotFoundException, SQLException {

	    Connection con = DBUtility.getConnection();

	    User user = null;
	    boolean unbanSuccessful = false;

	    user = new User();
	    String sql = "SELECT * FROM banned_user_table where userid=" + id;
	    Connection connection = DBUtility.getConnection();
	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);
	    if (resultSet.next()) {
	        user.setUserId(resultSet.getInt("userId"));
	        user.setUsername(resultSet.getString("username"));
	        user.setNrcNumber(resultSet.getString("nrcNumber"));
	        user.setEmail(resultSet.getString("gmail"));
	        user.setCareer(resultSet.getString("Career"));
	        user.setBalance(resultSet.getDouble("balance"));
	        user.setPassword(resultSet.getString("hashedPassword"));
	        user.setAccountNumber(resultSet.getString("accountNumber"));
	    }

	    String dsql = "DELETE FROM banned_user_table where userid=" + id;
	    PreparedStatement deleteStatement = con.prepareStatement(dsql);
	    int rowDeleted = deleteStatement.executeUpdate();
	    
	    if (rowDeleted > 0) {
	        String insql = "INSERT INTO usertable (username, nrcNumber, gmail, career, balance,hashedPassword, accountNumber)"
	                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement insertStatement = con.prepareStatement(insql);
	        insertStatement.setString(1, user.getUsername());
	        insertStatement.setString(2, user.getNrcNumber());
	        insertStatement.setString(3, user.getEmail());
	        insertStatement.setString(4, user.getCareer());
	        insertStatement.setDouble(5, user.getBalance());
	        insertStatement.setString(6, user.getPassword());
	        insertStatement.setString(7, user.getAccountNumber());    
	        int rowsInserted = insertStatement.executeUpdate();
	        if (rowsInserted > 0) {
	            unbanSuccessful = true;
	        }
	    }

	    return unbanSuccessful;
	}

	//THS
	
	public boolean addUserAmount(int id, double amount) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE usertable SET balance = balance +" + amount + " WHERE userId=" + id;
		Connection con = DBUtility.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		try {
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			return false;
		}
		return true;
	}
//    private ConnectionManager connectionManager;
//
//    public UserDao(ConnectionManager connectionManager) {
//        this.connectionManager = connectionManager;
//    }
//
//    public boolean createUser(User user) {
//        String sql = "INSERT INTO User (userId, username, email, password) VALUES (?, ?, ?, ?)";
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//        	preparedStatement.setInt(1, user.getId());
//            preparedStatement.setString(2, (String)user.getUsername());
//            preparedStatement.setString(3, (String) user.getEmail());
//            preparedStatement.setString(4, (String)user.getPassword());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace(); 
//            return false;
//        }
//    }
//
//    public User getUserByUserId(int userId) {
//        String sql = "SELECT * FROM User WHERE userId = ?";
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, userId);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return mapResultSetToUser(resultSet);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace(); 
//        }
//
//        return null; 
//    }
//
//
//    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
//        User user = new User();
//
//        user.setUserId(resultSet.getInt("userId"));
//        user.setUsername(resultSet.getString("username"));        
//        user.setEmail(resultSet.getString("email"));
//        user.setPassword(resultSet.getString("password"));
//        return user;
//    }

	 public boolean updateUserBalance(int id, double loan_amount) throws ClassNotFoundException {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtility.getConnection();
            String sql = "UPDATE usertable SET balance = balance + ? WHERE userId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, loan_amount);
            stmt.setInt(2, id);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions appropriately
        } finally {
            // Close the PreparedStatement and Connection
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return success;
    }

}
