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
import javax.swing.JList;

import java.util.ArrayList;

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

		String[] names = {"a","b"};
		JList requests = new JList(names);
		requests.setSelectedIndex(0);
		JLabel requestLabel = new JLabel("Freind Requests");
		JButton requestButton = new JButton ("Add Friend");
		requests.setBounds(270,400,165,50);
		requestLabel.setBounds(150,400,100,25);
		requestButton.setBounds(445,400,100,25);
		add(requests);
		add(requestLabel);
		add(requestButton);

		String[] friendslist = {"a","b"};
		JList friends = new JList(names);
		friends.setSelectedIndex(0);
		friends.setBounds(270,300,165,50);
		add(friends);


		friendsearchLabel.setBounds(150,300,100,25);
		messageLabel.setBounds(550, 640, 100, 25);
		createButton.setBounds(650, 630, 100, 45);
		logoutButton.setBounds(35, 630, 100, 45);
		meLabel.setBounds(519, 180, 100, 25);
		viewprofileButton.setBounds(580, 220, 150, 25);
		welcomeLabel.setFont(new Font("Monotype Corsiva",1,15));
		welcomeLabel.setBounds(60,170,100,25);
		nameText.setBounds(130,170,100,25);
		skpertawelLabel.setFont(new Font("PMingLiU",3,50));
		skpertawelLabel.setBounds(50, 80, 400, 30);
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

		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					get_main().get_login();
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		createButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					get_main().set_message(acc);
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

		this.profileAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					System.out.println("opening profile screen");
					get_main().set_profile(acc);
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		this.messageAction.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					System.out.println("opening messages screen");
					get_main().set_message(acc);
					dispose(); // closes the current screen when new one opens
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		this.drawAction.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("opening collab draw screen");
			}
		});
		setVisible(true);

	}

	 public static void main(String[] args) throws Exception {
	       //HomeGUI home = new HomeGUI();
	       //home.homesetup();

	       //home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //home.setVisible(true);

	    }
}
