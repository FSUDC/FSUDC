/*
 * File:    CustomizeProfilUI.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The user interface for users to customize their personal profile.
 */

package com.vaadin.fsudc;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.fsudc.ImageReceiver;

@SuppressWarnings("serial")
public class CustomizeProfileUI extends Window{
	
	public AbsoluteLayout profile;
	public ImageReceiver receiver;
	public Embedded imgPanel;
	public RichTextArea bio;
	public Upload upload;	
	public Button save;
	
	public CustomizeProfileUI (final Profile p) {
		
		super("Profile");
		
		// Window Position
		setPositionX(385);
    setPositionY(80); 
        
    // Layout Size
    profile = new AbsoluteLayout();
    profile.setWidth("600px");
    profile.setHeight("400px");        
            
    // Upload Image
    receiver = new ImageReceiver();         
    upload = new Upload("Upload Image Here", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);
		
		// User Image
		File path = new File(p.getUserPic());		
		imgPanel = new Embedded(p.getUser(), new FileResource(path));
    imgPanel.setHeight("150px");
    imgPanel.setWidth("150px");
		
    // User Bio 
		bio = new RichTextArea("About Me");
		bio.setValue(p.getUserBio());		
		bio.setWidth("500px");
		bio.setHeight("150px");
		
		// Save Button
		save = new Button("Save My Profile");
		save.setStyleName(BaseTheme.BUTTON_LINK);
		
		// Add components to layout
    profile.addComponent(imgPanel, "left: 50px; top: 50px;");
    profile.addComponent(upload, "left: 200px; top: 50px;");
    profile.addComponent(bio, "right: 50px; bottom: 30px;");
    profile.addComponent(save, "right: 250px; bottom: 10px;");
	        
    // Save button listener
    save.addClickListener(new Button.ClickListener() {
		public void buttonClick(ClickEvent event) {
					
		  UCProfile ucp = new UCProfile();
		  ucp.writeBio(bio.getValue());
			close();
			ucp.showProfile(p, "customize");
		}});
        
    // Set Layout 
    setContent(profile);
	}	
}
