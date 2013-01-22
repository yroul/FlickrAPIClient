package yroul.flickr.view.button;

import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
