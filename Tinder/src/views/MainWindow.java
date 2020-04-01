package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import models.Profile;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final String ICON_PATH = "/img/icon.png";
	private static final String TITLE = "Tinder";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private MainPanel mainPanel;
	
	public MainWindow(ActionListener lister) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
		
		setJMenuBar(new MenuBar(lister));
		
		mainPanel = new MainPanel();
		add(mainPanel, BorderLayout.CENTER);
	}
	
	public void initData(ArrayList<Profile> profileList, ActionListener listener) {
		mainPanel.init(profileList, listener);
	}
}