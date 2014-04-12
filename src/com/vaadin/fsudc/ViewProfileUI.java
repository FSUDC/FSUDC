/*
 * File:    ViewProfileUI.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The user interface to view a non editable user profile.
 */

package com.vaadin.fsudc;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class ViewProfileUI extends Window{
	
	public AbsoluteLayout profile;
	public Embedded imgPanel;
	public Button customize;
	public Panel bioPanel;
	public Label bio;		
	
	public ViewProfileUI (final Profile p) {
		
		super("Profile");
		
		// Set window position
		setPositionX(385);
    setPositionY(80);
		
    // Set profile size
		profile = new AbsoluteLayout();
		profile.setWidth("600px");
    profile.setHeight("400px");
        
    // Retrieve image file path & set size
    File path = new File(p.getUserPic());		
		imgPanel = new Embedded(p.getUser(), new FileResource(path));
    imgPanel.setHeight("150px");
    imgPanel.setWidth("150px");
        
    // Retrieve bio & set size
		bio = new Label(p.getUserBio(), ContentMode.HTML);		
		bioPanel = new Panel ("About Me");
		bioPanel.setWidth("500px");
		bioPanel.setHeight("150px");
		bioPanel.setContent(bio);
		
		// Add customize button
		customize = new Button("Customize My Profile");		
		customize.setStyleName(BaseTheme.BUTTON_LINK);
		        
		// Customize button click listener
    customize.addClickListener(new Button.ClickListener() {
		public void buttonClick(ClickEvent event) {
						
		// Open CustomizeProfileUI()
		UCProfile ucp = new UCProfile();
		ucp.showProfile(new Profile(p.getUser()), "customize");
		}}); 
		
    // Add components
    profile.addComponent(imgPanel, "left: 50px; top: 30px;");
    profile.addComponent(bioPanel, "left: 50px; bottom: 50px;");
    profile.addComponent(customize, "right: 240px; bottom: 10px;");
        
    // Set Layout
    setContent(profile);
	}	
}
