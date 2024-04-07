package com.cashnex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BannedUserDao {
    
    public List<String> getBannedUserEmails() throws SQLException, ClassNotFoundException {
        List<String> bannedGmails = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBUtility.getConnection();
            
            String sql = "SELECT gmail FROM banned_user_table";
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
}
