package yroul.flickr.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import yroul.flickr.FlickrAPIClient;
import yroul.flickr.model.Photo;
import yroul.flickr.model.PhotoSet;

public class MainWindow implements ActionListener,MouseListener  {

	private JFrame frame;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JPanel mainPanel;
	private JPanel eastPanel;
	private JPanel northPanel;
	private JPanel downLoadPanel;
	private DownLoadButton downloadButton;
	private JPanel picturePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSearch = new JLabel("Search");
		northPanel.add(lblSearch);
		
		txtSearch = new JTextField();
		northPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("search");
		btnSearch.addActionListener(this);
		northPanel.add(btnSearch);
		
		
		
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(0,5));
		
		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		eastPanel = new JPanel();
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		downLoadPanel = new JPanel();
		eastPanel.add(downLoadPanel, BorderLayout.SOUTH);
		
		picturePanel = new JPanel();
		eastPanel.add(picturePanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btnSearch on click
		if(e.getSource().equals(btnSearch)){
			String keywords = txtSearch.getText();
			if(!(keywords.isEmpty())){
				//remove old images
				mainPanel.removeAll();
				PhotoSet photoSet = FlickrAPIClient.searchPhotos(keywords);
				for(Photo p : photoSet.getPhoto()){
					ImagePanel imagePanel = new ImagePanel(FlickrAPIClient.getImageURL("Square",p.getId()),p.getId());
					imagePanel.addMouseListener(this);
					mainPanel.add(imagePanel);
				
				}
				northPanel.repaint();
				mainPanel.repaint();
				mainPanel.validate();
				
			}
			
		}
		if(e.getSource() == downloadButton){
			downloadButton.save();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof ImagePanel){
			ImagePanel target = (ImagePanel)e.getSource();
			this.picturePanel.removeAll();
			this.downLoadPanel.removeAll();
			ImagePanel picture = new ImagePanel(FlickrAPIClient.getImageURL("Medium",target.getImageId()),target.getImageId());
			this.picturePanel.add(picture);
			this.picturePanel.repaint();
			this.picturePanel.validate();
			this.downloadButton = new DownLoadButton(target.getImageId());
			downloadButton.addActionListener(this);
			this.downLoadPanel.add(this.downloadButton);
			
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
