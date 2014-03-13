package com.vaadin.fsudc;

import com.vaadin.ui.UI;

public class CtrForum {
	
	private ForumUI fui = new ForumUI();
	private Forum [] forum = new Forum[5];
	
	public CtrForum()  {
		
		UI.getCurrent().addWindow(fui);
		
		forumList();		
	}
	
	public void forumList(){
		
		for(int x = 1; x < 6; ++x)
		{
			String name = fui.table.getItem(x).toString();
			int id = x;
		  forum [x - 1] = new Forum(name, id);
		}
	}
}
