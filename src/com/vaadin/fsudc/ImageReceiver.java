/*
 * File:    ImageReceiver.java
 * Author:  Alicia Gambill
 * Date:    April 2014
 * Project: FSUDC
 *  
 * Description: The receiver used to upload images on CutomizeProfileUI().
 */

package com.vaadin.fsudc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class ImageReceiver implements Receiver, SucceededListener {

		public File file;
		public UCProfile uc;
		
		public ImageReceiver (){
			
			file = null;
			uc = new UCProfile();
		}
		
		public OutputStream receiveUpload(String filename, String mimeType) {
			
			String path = "C:\\Users\\Alicia\\Pictures\\FSUDC\\";
			FileOutputStream fos = null;
			
			try {
				// Retrieve, copy, and store file to new file path
				file = new File(path + filename);
				fos = new FileOutputStream(file);				
				
				// Send file name to UCProfile()
				uc.newImage(file.toString());				
				
			} catch (final java.io.FileNotFoundException e) {
				
				// File not found exception
				new Notification("Could not open file<br/>",
						e.getMessage(),
						Notification.Type.ERROR_MESSAGE)
				.show(Page.getCurrent());
				
				return null;
			}
			return fos; 
		}

		public void uploadSucceeded(SucceededEvent event) {
			
			// Notification message
			new Notification("Click 'Save My Profile' \n to view changes.",
					Notification.Type.HUMANIZED_MESSAGE)
			.show(Page.getCurrent());
		}
	};
