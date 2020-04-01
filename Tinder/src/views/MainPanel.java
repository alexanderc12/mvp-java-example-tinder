package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Profile;

public class MainPanel extends JPanel{

	private static final int COLUMN_NUMBER = 1;
	private static final long serialVersionUID = 1L;
	
	public void init(ArrayList<Profile> profileList,ActionListener listener) {
		removeAll();
		setLayout(new GridLayout(profileList.size(), COLUMN_NUMBER));
		for (Profile profile : profileList) {
			add(new ProfileCard(listener, profile));
		}
		revalidate();
	}
}