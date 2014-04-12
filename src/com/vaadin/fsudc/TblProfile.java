/*
 * File:    TblProfile.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The class that handles database interaction for the Profile table.
 */

package com.vaadin.fsudc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.vaadin.ui.UI;

public class TblProfile{
	
	private PreparedStatement stmt = null;
	private Statement statement = null;
	private Connection connect = null;
	private ResultSet result = null;
	
	public TblProfile () {
	}

	public void openTable() {
		
		connect = MySQLConnection.getConnection();
	}
	
	public void closeTable() {
		
		try {
            if (result != null) 
                result.close();
            
            if (statement != null) 
                statement.close();
            
            if (connect != null) 
                connect.close();
            
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void addProfile(Member mbr){
		
		String insert = "INSERT INTO Profile (userID, bio, image) VALUES (?, ?, ?)";
		String user = mbr.getProfile().getUser();
		String bio = mbr.getProfile().getUserBio();
		String imagePath = mbr.getProfile().getUserPic();
		
		openTable();
		
		// Add profile for new member
		try {
			stmt = connect.prepareStatement(insert);
			stmt.setString(1, user);
			stmt.setString(2, bio);
			stmt.setString(3, imagePath);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}
	}
	
	public void addBio (String bio) {
		
		String user = (String) UI.getCurrent().getSession().getAttribute("user");
		String update = "UPDATE Profile SET bio = '" + bio + "' WHERE userID = '" + user + "';";
		
		openTable();
		
		// Add user bio in profile table
		try {
			statement = connect.createStatement();
			statement.executeUpdate(update);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
	}
	
	public String showBio (String user) {
		
		String update = "SELECT bio FROM Profile WHERE userID = '" + user + "';";
		String bio = null;		
		
		openTable();
		
		// Return user bio stored in database
		try {
			statement = connect.createStatement();
			statement.execute(update);
			
			result = statement.getResultSet();
			
			while (result.next()) {
				bio = result.getString("bio");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
		return bio;
	}
	
	public void addImage (String img) {
		
		String update = "UPDATE Profile SET image = ? WHERE userID = ?";
		String user = (String) UI.getCurrent().getSession().getAttribute("user");
		
		openTable();
		
		// Add user image to profile table
		try {
			stmt = connect.prepareStatement(update);
			stmt.setString(1, img);
			stmt.setString(2, user);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}	
	}
	
	public String showImage (String user) {
		
		String update = "SELECT image FROM Profile WHERE userID = '" + user + "';";
		String image = null;		
		
		openTable();
		
		// Return user image stored in database
		try {
			statement = connect.createStatement();
			statement.execute(update);
			
			result = statement.getResultSet();
			
			while (result.next()) {
				image = result.getString("image");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
		return image;
	}
}
