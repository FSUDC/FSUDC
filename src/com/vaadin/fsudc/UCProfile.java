/*
 * File:    UCProfile.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The class that handles profile interaction.
 */

package com.vaadin.fsudc;

import com.vaadin.ui.UI;

public class UCProfile {

	public CustomizeProfileUI customize;
	public ViewProfileUI view;
	private TblProfile tbl;

	public UCProfile () {
		
		tbl = new TblProfile();
	}	

	public void writeBio (String bio){

		// Add bio to database
		tbl.addBio(bio);
	}
	
	public void newImage (String img){

		// Add image to database
		tbl.addImage(img);
	}

	public void showProfile(Profile p, String type) {

		// Retrieve user bio and image
		p.setUserBio(tbl.showBio(p.getUser())); 
		p.setUserPic(tbl.showImage(p.getUser()));
		
		// Show ViewProfileUI()
		if (type.equals("view"))
		{	
			view = new ViewProfileUI(p);
			UI.getCurrent().addWindow(view);	
		}
		
		// Show CustomizeProfileUI()
		if (type.equals("customize"))
		{	
			customize  = new CustomizeProfileUI(p);
			UI.getCurrent().addWindow(customize);	
		}
	}
}
