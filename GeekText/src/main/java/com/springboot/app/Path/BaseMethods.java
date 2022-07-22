package com.springboot.app.Path;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

public class BaseMethods {
    final String url = "";
    final String username = "";
    final String DBPassword = "";
    private String USER_ID;
    
    
    public String getUSER_ID() {
        return USER_ID;
    }
    public String getURL() {
    	return url;
    }
    public String getUsername() {
    	return username;
    }
    public String getDBPassword() {
    	return DBPassword;
    }
        
    public ResultSet SQLQuery(String query) { //Should be used for retrieving data from the database.
    	ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public void SQLUpdate(String update) { //Function should be called when inserting or dropping elements of the database
    	
    	int result;
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(update);
            if(result > 0) {
            	System.out.println("A new row has been added.\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean isTableEmpty(String Table) { //Checks to see if a queried table is empty or not
        try {
            String Query = "SELECT CASE WHEN EXISTS (SELECT * FROM "+ Table + " LIMIT 1) THEN 1 ELSE 0 END";
            ResultSet queryResult = SQLQuery(Query);
            System.out.println("NEXT");
            queryResult.next();
            return queryResult.getInt("case") != 1;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    


    
}


