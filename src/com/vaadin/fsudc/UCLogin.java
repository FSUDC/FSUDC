/*
 * File:    UCLogin.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The class that handles login interaction.
 */

package com.vaadin.fsudc;

import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.*;
import javax.mail.internet.*;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

public class UCLogin {
	
	public VerifyUI verifyForm;
	private TblMember dbTable;
	public LoginUI loginForm;
	
	@SuppressWarnings("serial")
	public UCLogin() {
		
		loginForm = new LoginUI();
		verifyForm = new VerifyUI();
		
		// Display login window
		UI.getCurrent().addWindow(loginForm);
		
		// Button listeners 
		loginForm.loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				validateEmail(loginForm.email.getValue());
		}});
		
		verifyForm.loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				validateCode(verifyForm.code.getValue());
		}});
	}
	
	public void validateEmail(String email) {
		
		String domain = "";
		
		// Email field blank
		if (email.isEmpty())
		{
			showError("Please enter you FSU CS email.");
			return;
		}
		
		// Check email domain
		StringTokenizer token = new StringTokenizer(email, "@");
	        	
		while(token.hasMoreElements())
			domain = (String) token.nextElement();
		
		if (domain.equals("cs.fsu.edu"))
		{
			boolean found = checkMember(email);
			
			// New User
			if (found == false)
				sendEmail(email);
			  
			// Close login window
			loginForm.close();	    
			
			// Display verification window
			UI.getCurrent().addWindow(verifyForm);
			
			// Set session for user
			UI.getCurrent().getSession().setAttribute("user", email);
		}
		
		// Non CS Email
		else
		{
			showError("Please enter a valid FSU CS email.");
			return;
		}	 
	}

	public void validateCode(String code) {
		
		// Correct Code
		if (code.equals("GoNoles"))
		{
			verifyForm.close();
			new CtrForum();			
			UI.getCurrent().addWindow(new MemberListUI());					
		}
		
		// Incorrect Code
		else
		{
			showError("The verification code you entered is not correct.");
			return;
		}
	}
	
	public boolean checkMember(String email) {
		
		// Look for member in database
		boolean found = false;
		dbTable = new TblMember();
		found = dbTable.findMember(email);
		
		return found;
	}
	
	public void showError(String errorString) {
		new Notification("<center>Login Error</center>",
			    "<br/>" + errorString,
			    Notification.Type.ERROR_MESSAGE, true)
			    .show(Page.getCurrent());
	}
	
	private void sendEmail(String to){	
				
		// Set email properties
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.live.com");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.port", "587");
		 
		// Login information
		Session session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("Insert Email Here","Insert Password Here");
					}
				});

		// Set up email message and send
	    try{
	    	Message msg = new MimeMessage(session);
	    	
	    	msg.setFrom(new InternetAddress("Insert Email Here"));
	    	
	    	msg.addRecipient(Message.RecipientType.TO,
	    			new InternetAddress(to));

	    	msg.setSubject("FSUDC Verification Code");

	    	msg.setText("Welcome to FSUDC!\n\n" +
	    	"The verification code is: GoNoles\n\n" +
	    	"Please keep this email because you will need to enter this code each time you visit FSUDC.\n\n" +
	    	"Have A Great Day!\n\n" +
	    	"Students of the Web");

	    	Transport.send(msg);
	        
	    	System.out.println("Your email has sent.");
	    	
	   // Email did not send
	    }catch (MessagingException ex) {
	    	ex.printStackTrace();
	    	System.out.println("Your email did not send.");
	    }
	}
}
