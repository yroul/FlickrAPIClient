package yroul.flickr.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import yroul.flickr.FlickrAPIClient;


public class DownLoadButton extends JButton {

	private String imageId;
	public DownLoadButton(String imageId) {
		super();
		this.setText("Download");
		this.imageId = imageId;
		this.setIcon(new ImageIcon(this.getClass().getResource("download.png")));
		
	}

	public void save(){
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Save");
		System.out.println("save imageId : "+imageId);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            
           
			try {
				//Save file
	            String picture = FlickrAPIClient.getImageURL("Original",imageId);
				URL pictureURL = new URL(picture);
				FileUtils.copyURLToFile(pictureURL, file);
				
			}catch(IllegalArgumentException e){
				//fallback, try to get the large image
				try {
					//Save file
		            String picture = FlickrAPIClient.getImageURL("Large",imageId);
					URL pictureURL = new URL(picture);
					FileUtils.copyURLToFile(pictureURL, file);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
        } else {
           System.out.println("error");
        }
		
	}
	
}
