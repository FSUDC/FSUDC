/*
 * File:    TblMember.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The class that handles database interaction for the Member table.
 */

package com.vaadin.fsudc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class TblMember extends Window {
	
	private Statement statement = null;
	private Connection connect = null;
	private ResultSet result = null;
	private Member member = null;	
	
	public TblMember () {
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
	
	public boolean findMember(String user) {
		
		String query = "SELECT * FROM Member WHERE csEmail = '" + user + "';";
		boolean found = false;
		
		openTable();
		
		// Search for member
		try {
			statement = connect.createStatement();
			result= statement.executeQuery(query);
			
			// New Member
			if (!result.next())
			{
				member = new Member(user);
				addMember(member);
			}
			
			// Already a member
			else
				found = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
		return found;
	}
	
	public String [] listOfMembers() {
		
		String query = "SELECT csEmail FROM Member;";
		String [] user = new String[countMembers()];
		int count = 0;		
		
		openTable();
		
		// Retrieve members in database
		try {
			statement = connect.createStatement();
			statement.execute(query);
			
			result = statement.getResultSet();
			
			// Store members in array
			while (result.next()) {
				user[count] = result.getString("csEmail");
				++count;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
		return user;
	}
	
	public int countMembers () {
		
		String query = "SELECT COUNT(*) FROM Member;";
		int count = 0;
		
		openTable();
		
		// Count members
		try {
			statement = connect.createStatement();
			statement.execute(query);
			
			result = statement.getResultSet();

			while (result.next()) {
				count= result.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
		return count;
	}
	
	public void addMember (Member mbr) {
		
		String query = "INSERT INTO Member (csEmail, loginStatus)" +
				" VALUES ('" + mbr.getCSEmail() + "', '" + mbr.getStatus() + "');";
		
		openTable();
		
		// Add member
		try {
			statement = connect.createStatement();
			statement.executeUpdate(query);
			
			// Add member profile
			TblProfile profile = new TblProfile();
			profile.addProfile(mbr);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}			
	}
}
