package views;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import presenters.Event;

public class MenuBar extends JMenuBar{

	private static final String TEXT_ADD_PROFILE = "Add profile";
	private static final String TXT_FILE = "File";
	private static final long serialVersionUID = 1L;

	public MenuBar(ActionListener listener) {
		JMenu menuFile = new JMenu(TXT_FILE);
		add(menuFile);
		
		JMenuItem itemShowAdd = new JMenuItem(TEXT_ADD_PROFILE);
		itemShowAdd.addActionListener(listener);
		itemShowAdd.setActionCommand(Event.SHOW_ADD_PROFILE.toString());
		menuFile.add(itemShowAdd);
	}
}