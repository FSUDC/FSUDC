package com.vaadin.fsudc;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial"})

public class ForumUI extends Window {

	public Table table = new Table();
        public HorizontalLayout forumLayout;
	
	public ForumUI () {
		
	super("FSUDC Discussion Board");    	
    	center();
    	
        forumLayout = new HorizontalLayout();  
       
        table.setWidth("800px");
        table.setHeight("350px");
        table.setSelectable(true);
        table.setImmediate(true);
        table.addContainerProperty("Forum", String.class,  null);
        table.setColumnAlignment("Forum", Align.CENTER);
        
        table.addItem(new Object[] {"General Programming"}, new Integer(1));
        table.addItem(new Object[] {"FSU Computer Science Courses"}, new Integer(2));
        table.addItem(new Object[] {"FSU Computer Science Events"}, new Integer(3));
        table.addItem(new Object[] {"Computer Science Careers"}, new Integer(4));
        table.addItem(new Object[] {"Computer Science News"}, new Integer(5));

        forumLayout.addComponent(table);

        setContent(forumLayout);      

	}
}
