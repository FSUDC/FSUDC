package com.vaadin.fsudc;

public class Member {
	
	private String csName;
	private Profile userProfile;
	private String status;
	
	public Member (String name) {
		
		setCSName(name);
		setProfile(new Profile(name));
		setStatus("active");
	}
	
	public void setCSName(String name) {
		
		csName = name;
	}
	
	public String getCSName() {
		
		return csName;
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