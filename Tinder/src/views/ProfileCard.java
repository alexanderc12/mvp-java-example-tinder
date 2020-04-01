package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import models.Profile;
import presenters.Event;

public class ProfileCard extends JPanel{

	private static final Color COLOR_LIGTH_GRAY = Color.decode("#969696");
	private static final Border BTN_LINE_BORDER = BorderFactory.createLineBorder(
			COLOR_LIGTH_GRAY, 1, true);
	public static final Color COLOR_RED = Color.decode("#ef5734");
	public static final Color COLOR_GREEN = Color.decode("#2baf2b");
	public static final Color COLOR_GRAY = Color.decode("#BDBDBD");
	public static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 25);
	private static final long serialVersionUID = 1L;
	private static final String TXT_LIKE = "Like";
	private static final String TXT_DISLIKE = "Dislike";
	private static final String LIKE_ICON_PATH = "/img/like.png";
	private static final String DISLIKE_ICON_PATH = "/img/dislike.png";
	
	public ProfileCard(ActionListener listener, Profile profile) {
		setLayout(new BorderLayout());
		setBackground(COLOR_GRAY);

		JLabel lbName = new JLabel(profile.getName(),SwingConstants.CENTER);
		lbName.setFont(MAIN_FONT);
		lbName.setForeground(Color.WHITE);
		add(lbName, BorderLayout.PAGE_START);
		
		JLabel lbImg = new JLabel();
		Image image= new ImageIcon(profile.getImgPath()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lbImg.setIcon(new ImageIcon(image));
		lbImg.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbImg, BorderLayout.CENTER);

		switch (profile.getStatus()) {
			case NEW:
				createButtonBar(listener, profile);
				break;
			case LIKE:
				setBackground(COLOR_GREEN);
				break;
			case DISLIKE:
				setBackground(COLOR_RED);
				break;
		}
	}

	private void createButtonBar(ActionListener listener, Profile profile) {
		JPanel panelButtons = new JPanel(new GridLayout(1, 2));
		createDislikeButton(listener, profile, panelButtons);
		createLikeButton(listener, profile, panelButtons);
		add(panelButtons, BorderLayout.PAGE_END);
	}

	private void createLikeButton(ActionListener listener, Profile profile, JPanel panelButtons) {
		JButton btnLike = new JButton(new ImageIcon(getClass().getResource(LIKE_ICON_PATH)));
		btnLike.addActionListener(listener);
		btnLike.setToolTipText(TXT_LIKE);
		btnLike.setBackground(COLOR_GRAY);
		btnLike.setActionCommand(Event.LIKE.toString());
		btnLike.setName(String.valueOf(profile.getId()));
		btnLike.setFont(MAIN_FONT);
		btnLike.setFocusable(false);
		btnLike.setBorder(BTN_LINE_BORDER);
		panelButtons.add(btnLike);
	}

	private void createDislikeButton(ActionListener listener, Profile profile, JPanel panelButtons) {
		JButton btndislike = new JButton(new ImageIcon(getClass().getResource(DISLIKE_ICON_PATH)));
		btndislike.addActionListener(listener);
		btndislike.setToolTipText(TXT_DISLIKE);
		btndislike.setBackground(COLOR_GRAY);
		btndislike.setActionCommand(Event.DISLIKE.toString());
		btndislike.setName(String.valueOf(profile.getId()));
		btndislike.setFont(MAIN_FONT);
		btndislike.setFocusable(false);
		btndislike.setBorder(BTN_LINE_BORDER);
		panelButtons.add(btndislike);
	}
}