import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;

public class SelectDrawGUI extends GUI {

	public SelectDrawGUI(Account acc) throws Exception {

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


		JButton startButton = new JButton("Start");
		startButton.setBounds(20, 80, 80, 25);
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name;
				Account acc2;
				try
				{
					name = userText.getText();
					ReadWriteAccount rwa = new ReadWriteAccount("data.db");
					acc2 = rwa.read(name);
					get_main().set_draw(acc, acc2);
					dispose();
				}
				catch(Exception f)
				{
					System.out.println(f);
				}
			}
		});

		panel.add(startButton);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception
	{
		//SelectDrawGUI l = new SelectDrawGUI();
		//l.setVisible(true);
	}
}
