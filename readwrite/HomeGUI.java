/**
* TODO: add Account as an argument for the current user
*       do this with all GUIs except GUI.java, LoginGUI.java and CreateAccountGUI.java
*
*
*
*/

import java.awt.Dimension;

import java.awt.Font;
import java.awt.image.BufferedImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class HomeGUI extends GUI {


	private static final long serialVersionUID = 1L;

	public HomeGUI(Account acc) throws Exception {
		super();
		homesetup(acc);
	}

	public void homesetup(Account acc) throws Exception{
		JLabel friendsearchLabel = new JLabel("Friend Search:");
		JLabel contactsearchLabel = new JLabel("Contact Search:");
		JLabel meLabel = new JLabel("Me:");
		JLabel messageLabel = new JLabel("Create Message:");
		JLabel welcomeLabel = new JLabel ("Welcome:");
		JLabel skpertawelLabel = new JLabel ( "SKYPERTAWE");
		JTextField friendsearhText = new JTextField(20);
		JTextField contactsearhText = new JTextField(20);
		JTextField nameText = new JTextField(acc.getUsername());
		JButton friendsearchButton = new JButton("Search");
		JButton contactsearchButton = new JButton("Search");
		JButton logoutButton = new JButton("Log out");
		JButton createButton = new JButton ("Create");
		JButton viewprofileButton = new JButton("View own profile");
		JPanel login1Button = new JPanel();
		ProfileImagePanel profileImage = new ProfileImagePanel(acc.getImgPath());
		profileImage.setPreferredSize(new Dimension(200,200));
		setLayout(null);
		setSize(800,800);

		friendsearchLabel.setBounds(150,300,100,25);
	    friendsearhText.setBounds(250,300,165,25);
		friendsearchButton.setBounds(420, 300, 80, 25);
		contactsearchLabel.setBounds(150,400,100,25);
		contactsearhText.setBounds(250,400,165,25);
		contactsearchButton.setBounds(420, 400, 80, 25);
		messageLabel.setBounds(550, 690, 100, 25);
		createButton.setBounds(650, 680, 100, 45);
		logoutButton.setBounds(35, 680, 100, 45);
		meLabel.setBounds(480, 180, 100, 25);
		viewprofileButton.setBounds(580, 220, 150, 25);
		welcomeLabel.setFont(new Font("Monotype Corsiva",1,15));
		welcomeLabel.setBounds(75,170,100,25);
		nameText.setBounds(130,170,100,25);
		skpertawelLabel.setFont(new Font("PMingLiU",1,25));
		skpertawelLabel.setBounds(50, 80, 250, 30);
		profileImage.setBounds(550,15,200,200);

		viewprofileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					get_main().set_profile(acc);
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		add(friendsearchLabel);
		add(friendsearhText);
		add(friendsearchButton);
		add(contactsearchLabel);
		add(contactsearhText);
		add(contactsearchButton);
		add(login1Button);
		add(messageLabel);
		add(createButton);
		add(logoutButton);
		add(meLabel);
		add(viewprofileButton);
		add(welcomeLabel);
		add(skpertawelLabel);
		add(profileImage);
		add(nameText);

		setVisible(true);

	}

	 public static void main(String[] args) throws Exception {
	       //HomeGUI home = new HomeGUI();
	       //home.homesetup();

	       //home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //home.setVisible(true);

	    }
}
