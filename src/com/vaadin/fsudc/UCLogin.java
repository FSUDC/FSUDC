package com.vaadin.fsudc;

import java.sql.SQLException;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

public class UCLogin {
	
	private LoginUI loginForm = new LoginUI();
	private TblMember dbTable;
	private JSch jsch = new JSch();
	private String host = "shell.cs.fsu.edu"; 
	
	@SuppressWarnings("serial")
	public UCLogin() {
	
		UI.getCurrent().addWindow(loginForm);
		
		loginForm.loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				validateLogin(loginForm.username.getValue(), loginForm.password.getValue());
		}});			
	}
	
	public void validateLogin(String user, String pass) {
		
		if (user.isEmpty())
		{
			showError("Username field is blank.");
			return;
		}
		
		if (pass.isEmpty())
		{
			showError("Password field is blank.");
			return;
		}
			
		try {
			Session session = jsch.getSession(user, host, 22);
			session.setPassword(pass);
			
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
	
			session.connect();			
			session.disconnect();
	        	
	        loginForm.close();	        
	        checkMember(user);
	        
	        new CtrForum();
		
		} catch (JSchException e) {			
			showError("Please enter your <i>FSU CS</i> credentials");			
			e.printStackTrace();
			return;			
		} 		
	}
	
	public void checkMember(String name) {
		
		try {
			dbTable = new TblMember();
			dbTable.findMember(name);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showError(String errorString) {
		new Notification("<center>Login Error</center>",
			    "<br/>" + errorString,
			    Notification.Type.WARNING_MESSAGE, true)
			    .show(Page.getCurrent());
	}
}
