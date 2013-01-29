package yroul.flickr.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import yroul.flickr.core.FlickrAPIClient;
import yroul.flickr.model.Photo;
import yroul.flickr.model.PhotoSet;
import yroul.flickr.view.button.DownLoadButton;
import yroul.flickr.view.label.CustomLabel;
/**
 * 
 * @author yroul
 * 
 * Main window of the applciation
 *
 */
public class MainWindow implements ActionListener,MouseListener,KeyListener  {

	private JFrame frame;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JPanel mainPanel;
	private JPanel eastPanel;
	private JPanel northPanel;
	private JPanel downLoadPanel;
	private DownLoadButton downloadButton;
	private JPanel picturePanel;
	private final static Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
	private JProgressBar progressBar;
	private static String keywords;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String toSearch;
		try{
			toSearch = args[0];
		}catch(Exception e){
			toSearch = null;
		}
		keywords = toSearch;
		
		LOGGER.setLevel(Level.INFO);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
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
		frame.setTitle("Flickr Explorer");
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/flickr_logo.png")));
		
		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSearch = new JLabel("Search in ");
		northPanel.add(lblSearch);
		
		CustomLabel flickrLogo = new CustomLabel("flickr_logo.png");
		northPanel.add(flickrLogo);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(this);
		northPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("search");
		btnSearch.addActionListener(this);
		northPanel.add(btnSearch);
		
		progressBar = new JProgressBar(0,Integer.parseInt(FlickrAPIClient.photosPerPage));
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		northPanel.add(progressBar);
		
		
		
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
		
		if(keywords != null){
			txtSearch.setText(keywords);
			search(keywords);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//button search click
		if(e.getSource().equals(btnSearch)){
			search(this.txtSearch.getText());
		}
		//button download click
		if(e.getSource() == downloadButton){
			save();
		}
			
			
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof ImageLabel){
			ImageLabel target = (ImageLabel)e.getSource();
			this.picturePanel.removeAll();
			this.downLoadPanel.removeAll();
			ImageLabel picture = new ImageLabel(FlickrAPIClient.getImageURL("Medium",target.getImageId()),target.getImageId());
			this.picturePanel.add(picture);
			this.picturePanel.repaint();
			this.picturePanel.validate();
			this.downloadButton = new DownLoadButton(target.getImageId());
			downloadButton.addActionListener(this);
			this.downLoadPanel.add(this.downloadButton);
			downLoadPanel.revalidate();
			downLoadPanel.repaint();
			
			
		}
		
	}
	/**
	 * Start search in flickr api
	 */
	private void search(final String toSearch){
		progressBar.setVisible(true);
		progressBar.setValue(0);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				String keywords = toSearch;
				if(!(keywords.isEmpty())){
					//remove old images
					mainPanel.removeAll();
					PhotoSet photoSet = FlickrAPIClient.searchPhotos(keywords);
					LOGGER.info("PhotoSet recu");
					int i=1;
					for(Photo p : photoSet.getPhoto()){
						ImageLabel imagePanel = new ImageLabel(FlickrAPIClient.getImageURL("Square",p.getId()),p.getId());
						imagePanel.addMouseListener(MainWindow.this);
						mainPanel.add(imagePanel);
						LOGGER.info("Photos "+i+"/"+FlickrAPIClient.photosPerPage);
						i++;
						northPanel.revalidate();
						northPanel.repaint();
						mainPanel.revalidate();
						mainPanel.repaint();
						MainWindow.this.progressBar.setValue(MainWindow.this.progressBar.getValue()+1);
					
					}
					
					
				}
			}
			
		});
		t.start();
		
	}
	/**
	 * Save image
	 */
	private void save(){
		downloadButton.save();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if( e.getKeyCode() == KeyEvent.VK_ENTER){
			search(MainWindow.this.txtSearch.getText());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
