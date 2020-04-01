package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import models.ProfileService;
import persistence.FileManager;
import views.AddProfileDialog;
import views.MainWindow;

public class Presenter implements ActionListener{
	
	private ProfileService profileService;
	private MainWindow mainWindow;
	private AddProfileDialog addProfileDialog;
	
	public Presenter() {
		profileService = new ProfileService();
		mainWindow = new MainWindow(this);
		refreshList();
		mainWindow.setVisible(true);
		addProfileDialog = new AddProfileDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Event.valueOf(e.getActionCommand())) {
		case LIKE:
			like(getProfileId(e));
			break;
		case DISLIKE:
			dislike(getProfileId(e));
			break;
		case SHOW_ADD_PROFILE:
			showAddProfileDialog();
			break;
		case ADD_PROFILE:
			addProfile();
			break;
		default:
			break;
		}
	}
	
	private void addProfile() {
		try {
			profileService.addProfile(addProfileDialog.getProfile());
			addProfileDialog.setVisible(false);
			saveProfiles();
		} catch (NumberFormatException | IOException | URISyntaxException | SAXException | ParserConfigurationException
				| TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
	}

	private void saveProfiles() {
		FileManager.write(profileService.getProfileList());
		refreshList();
	}

	private void showAddProfileDialog() {
		addProfileDialog.cleanForm();
		addProfileDialog.setVisible(true);
	}

	private int getProfileId(ActionEvent e) {
		return Integer.parseInt(((JButton)e.getSource()).getName());
	}
	
	private void refreshList() {
		mainWindow.initData(profileService.getProfileList(), this);
	}

	private void dislike(int id) {
		try {
			profileService.dislike(id);
			saveProfiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void like(int id) {
		try {
			profileService.like(id);
			saveProfiles();
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}