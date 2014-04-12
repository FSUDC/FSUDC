/*
 * File:    LoginUI.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The user interface for login.
 */

package com.vaadin.fsudc;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})
public class LoginUI extends Window {
 
	public CustomLayout windowLayout;
  public Button loginButton;
  public TextField email;
	
	public LoginUI () {
		
		super("Login");  
		
		// Window position
  	center();
    	
  	// Initialize
  	windowLayout = new CustomLayout("loginLayout");
  	loginButton = new Button("Login");
  	email = new TextField();  	
    	      
  	// Add components to layout
    windowLayout.addComponent(email, "email");
    windowLayout.addComponent(loginButton, "login");    
        
    // Require email and focus
    email.setRequired(true);        
    email.focus();
        
    // Set layout
    setContent(windowLayout);
	}
}
