package com.vaadin.fsudc;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("fsudc")

public class FsudcUI extends UI {
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FsudcUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		CustomLayout layout = new CustomLayout("Main");
		layout.setSizeFull();
		setContent(layout);
		
		//new UCLogin();  Until lockout problems resolved
		new CtrForum();
	}
}
