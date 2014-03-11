package com.vaadin.fsudc;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})

public class LoginUI extends Window {

    public TextField username = new TextField();
    public PasswordField password = new PasswordField();
    public Button loginButton = new Button("Login");
    public CustomLayout windowLayout;
    public Boolean click = false;
	
	public LoginUI () {
		
	super("Login");    	
    	center();
    	
        windowLayout = new CustomLayout("loginLayout");      

        windowLayout.addComponent(username, "username");
        windowLayout.addComponent(password, "password");
        windowLayout.addComponent(loginButton, "login");
        
        setContent(windowLayout);
        
        username.setRequired(true);
        password.setRequired(true);
        
        username.focus();
	}
}
