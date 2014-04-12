/*
 * File:    MemberListUI.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The user interface that lists current FSUDC members.
 */

package com.vaadin.fsudc;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class MemberListUI extends Window{

	public VerticalLayout mbrLayout;
	private TblMember mbrTable;	
	private UCProfile profile;
	private String [] users;
	private Member mbr;
	public Button b;		
	
	public MemberListUI () {
		
		super("FSUDC Members");		
		
		// Window position and size
		setHeight("200px");
		setPositionX(50);
        setPositionY(150); 
        
        // Initialize
		mbrLayout = new VerticalLayout();		
		mbrTable = new TblMember();
		profile = new UCProfile();
		users = mbrTable.listOfMembers();		
		
		// List members
		for (int num = 0; num < mbrTable.countMembers(); ++num){
			
			// Create a button for each member
			b = new Button(users[num]);
			b.setCaption(users[num]);
			b.setDescription("View " + users[num] + "'s Profile");
			b.setStyleName(BaseTheme.BUTTON_LINK);
			
			// Customize and add button to layout
			mbrLayout.addComponent(b);
			mbrLayout.setSpacing(true);
			mbrLayout.setComponentAlignment(b, Alignment.TOP_CENTER);
			
			// Member button listener
			b.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				// Create member object and show ViewProfileUI()
				mbr = new Member(event.getButton().getCaption());
				profile.showProfile(new Profile(mbr.getCSEmail()), "view");
			}});	
		}
		
		// Set Layout 
       setContent(mbrLayout);      
	}		
}
