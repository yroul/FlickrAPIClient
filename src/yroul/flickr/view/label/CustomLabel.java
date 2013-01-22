package yroul.flickr.view.label;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public  class CustomLabel extends JLabel {

	public CustomLabel(String iconeName) {
		super();
		this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/flickr_logo.png"))));
	}
	
}
