package views;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import models.Profile;
import models.ProfileService;
import presenters.Event;

public class AddProfileDialog extends JDialog{

	private static final String EMPTY_STRING = "";
	private static final int SIZE = 500;
	private static final String TXT_NAME = "Name";
	private static final long serialVersionUID = 1L;
	private static final String TXT_ADD_PROFILE = "Add";
	private JTextField txName;
	private JTextArea txImg;

	public AddProfileDialog(ActionListener listener) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(SIZE, SIZE);
		setModal(true);
		
		createJTextFieldName();
		createImgSelector();
		createAddButton(listener);
	}

	private void createAddButton(ActionListener listener) {
		JButton btnAddProfile = new JButton(TXT_ADD_PROFILE);
		btnAddProfile.addActionListener(listener);
		btnAddProfile.setForeground(Color.WHITE);
		btnAddProfile.setBackground(ProfileCard.COLOR_GREEN);
		btnAddProfile.setActionCommand(Event.ADD_PROFILE.toString());
		btnAddProfile.setFocusable(false);
		add(btnAddProfile);
	}

	private void createImgSelector() {
		txImg = new JTextArea();
		txImg.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : droppedFiles) {
		                txImg.setText(file.getPath());
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		add(txImg);
	}

	private void createJTextFieldName() {
		txName = new JTextField();
		txName.setBorder(BorderFactory.createTitledBorder(TXT_NAME));
		add(txName);
	}
	
	public Profile getProfile() throws NumberFormatException, IOException, URISyntaxException, SAXException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		return ProfileService.createProfile(txName.getText(), txImg.getText());
	}
	
	public void cleanForm() {
		txImg.setText(EMPTY_STRING);
		txName.setText(EMPTY_STRING);
	}
}