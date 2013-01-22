package yroul.flickr.view.button;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class AbstractCustomButton extends JButton {

	private String iconName;

	public AbstractCustomButton(String iconeName,String text) {
		super();
		this.iconName = iconeName;
		this.setText(text);
		this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/"+iconeName))));
		
	}
	
}
