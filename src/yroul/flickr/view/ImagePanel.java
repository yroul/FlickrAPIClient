package yroul.flickr.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImagePanel extends JLabel implements ActionListener {

	private BufferedImage image = null;
	private String imageId;
	
	/**
     * Constructeur
     * @param file nom du fichier
     */
    public ImagePanel(String fileURL,String id) {
    	try {                
            image = ImageIO.read(new URL(fileURL));
            this.setIcon(new ImageIcon(image));
            this.imageId = id;
         } catch (IOException e) {
              e.printStackTrace();
         }
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this){
			System.out.println("clicked on photo !");
		}
		
	}
	public String getImageId(){
		return this.imageId;
	}
   
}
