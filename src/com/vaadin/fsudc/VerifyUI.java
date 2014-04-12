/*
 * File:    VerifyUI.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The user interface that requests verification code.
 */

package com.vaadin.fsudc;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})
public class VerifyUI extends Window {

	public CustomLayout windowLayout;
	public PasswordField code;
    public Button loginButton;
    	
	public VerifyUI () {
		
		super("Verification");    	
		
		// Window position
    	center();
    		
    	// Layout
		windowLayout = new CustomLayout("verifyLayout");      
		
		// Initialize
		code  = new PasswordField();
		loginButton = new Button("Login");
		
		// Add components
        windowLayout.addComponent(code, "code");
        windowLayout.addComponent(loginButton, "login");
        
        // Set code require and focus
        code.setRequired(true);        
        code.focus();
        
        // Set layout
        setContent(windowLayout);
	}
}
	
