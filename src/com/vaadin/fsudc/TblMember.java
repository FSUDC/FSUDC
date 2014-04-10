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
	
	public String [] listOfMembers() {
		
		String update = "SELECT csEmail FROM Member;";
		String [] user = new String[countMembers()];
		int count = 0;		
		
		openTable();
		
		try {
			statement = connect.createStatement();
			statement.execute(update);
			
			result = statement.getResultSet();
			
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
		
		int count = 0;
		String update = "SELECT COUNT(*) FROM Member;";
		
		openTable();
		
		try {
			statement = connect.createStatement();
			statement.execute(update);
			
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
		
		String update = "INSERT INTO Member (csEmail, loginStatus)" +
				" VALUES ('" + mbr.getCSEmail() + "', '" + mbr.getStatus() + "');";
		
		openTable();
		
		try {
			statement = connect.createStatement();
			statement.executeUpdate(update);
			
			TblProfile profile = new TblProfile();
			profile.addProfile(mbr);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeTable();
		}			
	}
}
