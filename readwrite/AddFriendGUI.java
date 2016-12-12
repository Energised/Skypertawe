import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class AddFriendGUI extends GUI {

	public AddFriendGUI(Account acc) throws Exception {
		super();
		addfriendsetup(acc);
	}

	public void addfriendsetup(Account acc) throws Exception{
		JLabel userLabel = new JLabel("Username");
		JTextField userText = new JTextField(20);
		JButton addButton = new JButton("Add");
		JPanel panel = new JPanel();
		setSize(400,200);

		userLabel.setBounds(20,20,80,25);
		userText.setBounds(100,20,165,25);
	    addButton.setBounds(20,80,80,25);

		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<Account> friends = new ArrayList<Account>();
				String username;
				Account acc2;
				friends.add(acc);
				try
				{
					username = userText.getText();
					ReadWriteAccount rwa = new ReadWriteAccount("data.db");
					ReadWriteFriends rwf = new ReadWriteFriends("data.db");
					acc2 = rwa.read(username);
					friends.add(acc2);
					rwf.write(friends);
					System.out.println("added friendship");
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		add(userLabel);
		add(userText);
		add(addButton);
		add(panel);

		setVisible(true);
	}

	 public static void main(String[] args) throws Exception {
	       //AddFriendGUI addfriend = new AddFriendGUI();
	       //addfriend.addfriendsetup();

	       //addfriend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //addfriend.setVisible(true);

	    }
}
