import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class ProfileGUI extends GUI {

	private static final long serialVersionUID = 1L;


	public ProfileGUI(Account acc) throws Exception {
		super();
		profilesetup(acc);
		setVisible(true);
	}

	public void profilesetup(Account acc) throws Exception{
		JLabel usernameLabel = new JLabel("Username:");
		JLabel firstnameLabel = new JLabel("First Name:");
		JLabel surnameLabel = new JLabel("Surname:");
		JLabel mobileLabel = new JLabel("Mobile Number:");
		JLabel birthdayLabel = new JLabel ("Birthday:");
		JLabel cityLabel = new JLabel ("City:");
		JLabel skpertawelLabel = new JLabel ( "SKYPERTAWE");
		JLabel profileLabel = new JLabel ( "Profile Image:");
		JTextField usernameText = new JTextField(acc.getUsername());
		JTextField firstnameText = new JTextField(acc.getFirstName());
		JTextField surnameText = new JTextField(acc.getSurname());
		JTextField mobileText = new JTextField(acc.getMobnumber());
		JTextField birthdayText = new JTextField(acc.getBirthDate());
		JTextField cityText = new JTextField(acc.getCity());
		JButton message = new JButton("Message");
		JButton history = new JButton("History");
		JButton drawing = new JButton("Drawing");
		JButton addfriend = new JButton("Add Friend");
		JButton edit = new JButton("Edit");
		JButton upload = new JButton("Upload");
		ProfileImagePanel profileImage = new ProfileImagePanel(acc.getImgPath());
		profileImage.setPreferredSize(new Dimension(200,200));
		setSize(800,800);
		setLayout(null);

		skpertawelLabel.setFont(new Font("PMingLiU",1,30));
		skpertawelLabel.setBounds(50, 80, 250, 30);
		profileLabel.setBounds(420,150,100,25);
		upload.setBounds(400,180,100,25);
		usernameLabel.setBounds(70, 300, 250, 30);
		firstnameLabel.setBounds(70, 350, 250, 30);
		surnameLabel.setBounds(70, 400, 250, 30);
		mobileLabel.setBounds(70, 450, 250, 30);
		birthdayLabel.setBounds(70, 500, 250, 30);
		cityLabel.setBounds(70, 550, 250, 30);
		usernameText.setBounds(170, 300, 250, 30);
		firstnameText.setBounds(170, 350, 250, 30);
		surnameText.setBounds(170, 400, 250, 30);
		mobileText.setBounds(170, 450, 250, 30);
		birthdayText.setBounds(170, 500, 250, 30);
		cityText.setBounds(170, 550, 250, 30);
		message.setBounds(500, 300, 250, 50);
		history.setBounds(500, 400, 250, 50);
		drawing.setBounds(500, 500, 250, 50);
		addfriend.setBounds(500, 600, 250, 50);
		edit.setBounds(50, 650, 250, 50);
		profileImage.setBounds(530,50,200,200);

		message.addActionListener(new ActionListener()
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

		history.addActionListener(new ActionListener()
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

		addfriend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					AddFriendGUI add = new AddFriendGUI(acc);
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		upload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					UploadGUI u = new UploadGUI(acc);
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		this.homeAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("opening home screen");
				try
				{
					get_main().set_home(acc);
					dispose();
				}
				catch(Exception e)
				{
					System.out.println(e);
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

		add(skpertawelLabel);
		add(usernameLabel);
		add(firstnameLabel);
		add(surnameLabel);
		add(mobileLabel);
		add(birthdayLabel);
		add(cityLabel);
		add(usernameText);
	    add(firstnameText);
		add(surnameText);
		add(mobileText);
		add(birthdayText);
		add(cityText);
		add(message);
		add(history);
		add(drawing);
		add(addfriend);
		add(edit);
		add(profileLabel);
		add(upload);
		add(profileImage);

		setVisible(true);

	}
	 public static void main(String[] args) throws Exception {

		   Account current_user = new Account("energised", "dan", "woolsey", "01234567891",
								 "01/01/1990", "swansea", 0, null, "energised.png");
		   ProfileGUI profile = new ProfileGUI(current_user);
	       //profile.profilesetup();

	       profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       profile.setVisible(true);

	    }
}
