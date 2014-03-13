package com.vaadin.fsudc;

public class Forum {
	
	private String name;
	private int id;
	
	public Forum (String n, int i) {
		
		setName(n);
		setID(i);
	}
	
	public void setName(String n) {
		
		name = n;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setID(int i) {
		
		id = i;
	}
	
	public int getID() {
		
		return id;
	}
}
