package com.vaadin.fsudc;

import java.sql.SQLException;
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
	
	private LoginUI loginForm = new LoginUI();
	private VerifyUI verifyForm = new VerifyUI();
	private TblMember dbTable;
	
	@SuppressWarnings("serial")
	public UCLogin() {
	
		UI.getCurrent().addWindow(loginForm);
		
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
		
		if (email.isEmpty())
		{
			showError("Please enter you FSU CS email.");
			return;
		}
		
		StringTokenizer token = new StringTokenizer(email, "@");
	        	
		while(token.hasMoreElements())
			domain = (String) token.nextElement();
		
		if (domain.equals("cs.fsu.edu"))
		{
			boolean found = checkMember(email);
			
			if (found == false)
				sendEmail(email);
			  
			loginForm.close();	    

			UI.getCurrent().addWindow(verifyForm);			
		}
		
		else
		{
			showError("Please enter a valid FSU CS email.");
			return;
		}	 
	}

	public void validateCode(String code) {
		
		if (code.equals("GoNoles"))
		{
			verifyForm.close();
			new CtrForum();
		}
		
		else
		{
			showError("The verification code you entered is not correct.");
			return;
		}
	}
	
	public boolean checkMember(String email) {
		
		boolean found = false;
		
		try {
			dbTable = new TblMember();
			found = dbTable.findMember(email);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return found;
	}
	
	public void showError(String errorString) {
		new Notification("<center>Login Error</center>",
			    "<br/>" + errorString,
			    Notification.Type.ERROR_MESSAGE, true)
			    .show(Page.getCurrent());
	}
	
	private void sendEmail(String to){	
				
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.live.com");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.port", "587");
		 
		Session session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("myemail@email.com","mypassword");
					}
				});

	    try{
	    	Message msg = new MimeMessage(session);
	    	
	    	msg.setFrom(new InternetAddress("myemail@email.com"));
	    	
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
	    	
	    }catch (MessagingException ex) {
	    	ex.printStackTrace();
	    	System.out.println("Your email did not send.");
	    }
	}
}
