package com.vaadin.fsudc;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})

public class VerifyUI extends Window {

	public PasswordField code = new PasswordField();
  public Button loginButton = new Button("Login");
  public CustomLayout windowLayout;
	
	public VerifyUI () {
		
		super("Verification");    	
    	center();
    		
		windowLayout = new CustomLayout("verifyLayout");      

        windowLayout.addComponent(code, "code");
        windowLayout.addComponent(loginButton, "login");
        
        setContent(windowLayout);
        
        code.setRequired(true);        
        code.focus();
	}
}
	
