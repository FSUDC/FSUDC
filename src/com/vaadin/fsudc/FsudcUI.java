package com.vaadin.fsudc;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("fsudc")

public class FsudcUI extends UI {
	
	public Button button = new Button ("Login");

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FsudcUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		AbsoluteLayout layout = new AbsoluteLayout();
		layout.setSizeFull();
		setContent(layout);

		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				new UCLogin();
			}});
		
		layout.addComponent(button, "right: 50px; top: 10px;");
	}
}