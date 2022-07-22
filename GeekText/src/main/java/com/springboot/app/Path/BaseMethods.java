package com.springboot.app.Path;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

public class BaseMethods {
    final String url = "jdbc:postgresql://localhost:5432/ProjectCEN";
    final String username = "postgres";
    final String DBPassword = "or8016ES";
    private String USER_ID;
    
    /*
    
    ConnectAndMenu() {
        this.USER_ID = null;
    }
    
    ConnectAndMenu(String IdArg) {
        this.USER_ID = IdArg;
    }

    ConnectAndMenu(String IdArg, String PassArg) { //Meant to be used when the profile integration is complete.
        this.USER_ID = IdArg;
    }
    */
    
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
        
    public ResultSet SQLQuery(String query) {
    	ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public void SQLUpdate(String update) {
    	
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
    
    
    public boolean isTableEmpty(String Table) {  //Needs editing, calling this function invokes ResultSet::Next, which causes any ResultSet using this function to iterate (This is an error).
        try {
            //String Query = "SELECT * FROM " + Table + " WHERE USER_ID = " + this.getUSER_ID() + ";";
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


