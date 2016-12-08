import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class LoginGUI extends GUI {

	private LoginGUI() {

		super();

		// make the window smaller, looks a bit nicer
		//setSize(500,180);

		// Creating JLabel
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(20,20,80,25);
		add(userLabel);

		// Creating text field where user is supposed to enter user name
		JTextField userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		add(userText);

		// Creating login button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(20, 80, 80, 25);
		add(loginButton);

		JPanel panel = new JPanel();
		add(panel);

		//setVisible(true);
	}

	public static void main(String[] args)
	{
		LoginGUI l = new LoginGUI();
		l.setVisible(true);
	}
}
