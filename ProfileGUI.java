import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField; 
import javax.swing.JPanel;

public class ProfileGUI extends GUI {


	private static final long serialVersionUID = 1L;
	

	private ProfileGUI() {
		super();
		
	}
	private void profilesetup(){
		JLabel usernameLabel = new JLabel("Username:");
		JLabel firstnameLabel = new JLabel("First Name:");
		JLabel surnameLabel = new JLabel("Surname:");
		JLabel mobileLabel = new JLabel("Mobile Number:");
		JLabel birthdayLabel = new JLabel ("Birthday:");
		JLabel cityLabel = new JLabel ("City:");
		JLabel skpertawelLabel = new JLabel ( "SKYPERTAWE");
		JLabel profileLabel = new JLabel ( "Profile Image:");
		JTextField usernameText = new JTextField(20);
		JTextField firstnameText = new JTextField(20);
		JTextField surnameText = new JTextField(20);
		JTextField mobileText = new JTextField(20);
		JTextField birthdayText = new JTextField(20);
		JTextField cityText = new JTextField(20);
		JButton message = new JButton("Message");
		JButton history = new JButton("History");
		JButton drawing = new JButton("Drawing");
		JButton addfriend = new JButton("Add Friend");
		JButton edit = new JButton("Edit");
		JButton upload = new JButton("Upload");
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
	}
	 public static void main(String[] args) {
	       ProfileGUI profile = new ProfileGUI();
	       profile.profilesetup();
	     
	       
	       profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       profile.setVisible(true);
	       
	    }
}