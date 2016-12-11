import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;

public class LoginGUI extends GUI {

	public LoginGUI() throws Exception {

		super();

		// make the window smaller, looks a bit nicer
		setSize(400,150);

		// add a new panel
		JPanel panel = new JPanel();
		add(panel);

		// Creating JLabel
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(20,20,80,25);
		panel.add(userLabel);

		// Creating text field where user is supposed to enter user name
		JTextField userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);

		// Creating login button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(20, 80, 80, 25);
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BST tree = null;
				String username = userText.getText();
				try
				{
					tree = get_main().get_tree();
				}
				catch(Exception f)
				{
					System.out.println(f);
					System.exit(0);
				}
				//System.out.println(username);
				ArrayList<Account> qry = tree.searchExact(username);
				if(qry.get(0).getUsername().equals(username))
				{
					Account current_user = qry.get(0);
					try
					{
						get_main().set_home(current_user);
						System.out.println("logging " + username + " in...");
						dispose();
					}
					catch(Exception g)
					{
						System.out.println(g);
					}
				}
			}
		});
		panel.add(loginButton);

		// Register an account button
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(20, 160, 80, 25);
		registerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					get_main().set_create();
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});
		panel.add(registerButton);

		setVisible(true);
	}

	public static void main(String[] args) throws Exception
	{
		LoginGUI l = new LoginGUI();
		l.setVisible(true);
	}
}
