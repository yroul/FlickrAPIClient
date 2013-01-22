package yroul.flickr.view.button;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import yroul.flickr.core.FlickrAPIClient;

/**
 * 
 * @author yroul
 * Custom button for the download button
 *
 */
public class DownLoadButton extends AbstractCustomButton {

	private String imageId;
	/**
	 * Constructor
	 * @param imageId the image id
	 */
	public DownLoadButton(String imageId) {
		super("download.png","Download");
		this.imageId = imageId;		
	}

	/**
	 * Open a JFileChooser dialog to define where to save the picture
	 */
	public void save(){
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Save");
		System.out.println("save imageId : "+imageId);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            
           
			
				//Save file
	            String picture = FlickrAPIClient.getImageURL("Original",imageId);
	            if (picture == null){
	            	picture = FlickrAPIClient.getImageURL("Large",imageId);
	            }
	            
	            if(picture == null){
	            	throw new IllegalArgumentException("Téléchargement de l'image impossible");
	            }else{
	            	URL pictureURL;
					try {
						pictureURL = new URL(picture);
						FileUtils.copyURLToFile(pictureURL, file);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	            }
	            
				
				
			
        } else {
           System.out.println("error");
        }
		
	}
	
}
