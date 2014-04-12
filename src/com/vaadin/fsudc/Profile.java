/*
 * File:    Profile.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The entity class that holds member profile information.
 */

package com.vaadin.fsudc;

public class Profile {
	
	private String user;
	private String userPic;
	private String userBio;
	
	public Profile (String u) {
		
		setUser(u);
		setUserPic("C:\\Users\\Alicia\\Pictures\\FSUDC\\pic1.png");
		setUserBio("Tell us something about yourself...");
	}
	
	public void setUser(String u) {
		
		user = u;
	}
	
	public String getUser() {
		
		return user;
	}	
	
	public void setUserPic(String pic) {
		
			userPic = pic;
	}
	
	public String getUserPic() {
		
		return userPic;
	}
	
	public void setUserBio(String bio) {
		
		userBio = bio;
	}
	
	public String getUserBio() {
		
		return userBio;
	}		
}
