package com.vaadin.fsudc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.vaadin.annotations.Theme;

@Theme("fsudc")
public class MySQLConnection {
	
	private static MySQLConnection mysql = new MySQLConnection();
    private static final String URL = "jdbc:mysql://localhost:3306/fsudc";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
    
    private MySQLConnection () {
    	
    	try {
            Class.forName(DRIVER_CLASS).newInstance();
            
        } catch (Exception e) {
            e.printStackTrace();
		}
    }
    
    private Connection createConnection () {
    	
    	Connection connect = null;
    	
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (SQLException e) {
            System.out.println("ERROR: Database connection unsuccessful.");
        }
        
        return connect;
    }   
    
    public static Connection getConnection() {
        return mysql.createConnection();
    }
}