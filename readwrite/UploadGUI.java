/**
* UploadGUI.java
*
* 
* NOTE: program must restart for image changes to take place
*
*/

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadGUI extends GUI {


	private static final long serialVersionUID = 1L;


	public UploadGUI(Account acc) throws Exception{
		super();
		uploadsetup(acc);

	}
	public void uploadsetup(Account acc) throws Exception{
		JLabel userLabel = new JLabel("Image name:");
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
				String img;
				try
				{
					img = userText.getText();
					ReadWriteAccount rwa = new ReadWriteAccount("data.db");
					rwa.write_string_column(acc.getUsername(), "profile_img", img);
					System.out.println("image changed");
					reset_main().set_profile(acc);
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
	 public static void main(String[] args) throws Exception{
	       //UploadGUI upload = new UploadGUI();
	       //upload.uploadsetup();
	       //upload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //upload.setVisible(true);
	    }
}
