package com.vaadin.fsudc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class TblMember extends Window {
	
	private Statement statement = null;
	private Connection connect = null;
	private ResultSet result = null;
	private Member member = null;	
	
	public TblMember () throws SQLException {
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
		
		boolean found = false;
		
		String query = "SELECT * FROM Member"
				+ " WHERE csEmail = '" + user + "';";
		
		openTable();
		
		try {
			statement = connect.createStatement();
			result= statement.executeQuery(query);
			
			if (!result.next())
			{
				member = new Member(user);
				addMember(member);
			}
			else
				found = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}
		
		return found;
	}
	
	public void addMember (Member mbr) {
		
		String update = "INSERT INTO Member (csEmail, loginStatus, profileID)"
				+ " VALUES ('"
				+ mbr.getCSEmail() + "', '" + mbr.getStatus() + "', profileID + 1);";
		
		openTable();
		
		try {
			statement = connect.createStatement();
			statement.executeUpdate(update);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}		
	}
	
	public void showError(String errorString) {
		new Notification("<center>Error</center>",
			    "<br/>" + errorString,
			    Notification.Type.WARNING_MESSAGE, true)
			    .show(Page.getCurrent());
	}
}
