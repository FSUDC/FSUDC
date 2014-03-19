package com.vaadin.fsudc;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})

public class LoginUI extends Window {

    public TextField email = new TextField();
    public Button loginButton = new Button("Login");
    public CustomLayout windowLayout;
	
    public LoginUI () {
	
	super("Login");    	
    	center();
    	
    	windowLayout = new CustomLayout("loginLayout");      

        windowLayout.addComponent(email, "email");
        windowLayout.addComponent(loginButton, "login");
        
        setContent(windowLayout);
        
        email.setRequired(true);        
        email.focus();
   }
}
