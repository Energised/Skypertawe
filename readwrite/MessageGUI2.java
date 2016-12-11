/* Author: Carl Thomas
 * This class creates a GUI interface for the client to select a User
 * in their friends list and send them a message
 *
 * TODO: needs to take an argument for the current user
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import java.util.ArrayList;

public class MessageGUI2 extends GUI {

	//List of users to be linked with database
	private JList users;
	private DefaultListModel listModel;

	//Output screen to show previous messages
	private JTextPane chat;

	//User Input
	private JTextField input;
	private JButton send;

	// reference to ReadWriteMessage
	ReadWriteMessage rwm = null;

	public MessageGUI2() throws Exception{

		//Uses GUI Superclass
		super();

		setTitle("Messages");
		setSize(1280, 720);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// getting all messages for the given user
		// test user until I can get an Account object passed between GUIs
		Account current_user = new Account("energised", "dan", "woolsey", "01234567891",
                                  "01/01/1990", "swansea", 0, null, "energised.png");
		ArrayList<TextMessage> txt_msgs = new ArrayList<TextMessage>();
		listModel = new DefaultListModel();
		try
		{
			rwm = new ReadWriteMessage("data.db","messages.txt");
			txt_msgs = rwm.read_text_messages(current_user.getUsername());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		for(TextMessage x : txt_msgs)
		{
			System.out.println(x.getSender());
			System.out.println(x.getMessageContent());
			listModel.addElement(x.getSender());
		}

		//This code represents the window displaying a list of friends
		users = new JList(listModel);
		users.setBackground(Color.YELLOW);
		JScrollPane userScroll = new JScrollPane(users);
		userScroll.setPreferredSize(new Dimension(300, 0));
		userScroll.setBorder(BorderFactory.createTitledBorder("Friends"));
		super.add(userScroll, BorderLayout.WEST);

		JPanel chatPanel = new JPanel(new BorderLayout());
		super.add(chatPanel, BorderLayout.CENTER);

		//This code represents the output window for previous messages
		chat = new JTextPane();
		chat.setEditable(false);
		chat.setBackground(Color.CYAN);
		JScrollPane chatScroll = new JScrollPane(chat);
		chatScroll.setBorder(BorderFactory.createTitledBorder("Messages"));
		chatPanel.add(chatScroll, BorderLayout.CENTER);

		//This code represents the text input box and send button
		JPanel sendPanel = new JPanel(new BorderLayout());
		super.add(sendPanel, BorderLayout.SOUTH);
		input = new JTextField();
		sendPanel.add(input, BorderLayout.CENTER);
		send = new JButton("Send");
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent sendMessage){
				try
				{
					String msg_content = input.getText();
					System.out.println(msg_content);
					// create instance of text message where we can get the current user
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		sendPanel.add(send, BorderLayout.EAST);
		setVisible(true);


	}
	public static void main(String[] args) throws Exception{
		MessageGUI2 menu = new MessageGUI2();
		menu.setVisible(true);
	}


}
