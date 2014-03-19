package com.vaadin.fsudc;

public class Member {
	
	private String csEmail;
	private Profile userProfile;
	private String status;
	
	public Member (String user) {
		
		setCSEmail(user);
		setProfile(new Profile(user));
		setStatus("active");
	}
	
	public void setCSEmail(String user) {
		
		csEmail = user;
	}
	
	public String getCSEmail() {
		
		return csEmail;
	}
	
	public void setProfile(Profile profile) {
		
		userProfile = profile;
	}
	
	public Profile getProfile() {
		
		return userProfile;
	}
	
	public void setStatus(String stat) {
		
		status = stat;
	}
	
	public String getStatus() {
		
		return status;
	}
}
