/* Author: Carl Thomas
 * This class creates a GUI interface for the client to select a User
 * in their friends list and send them a message
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;


public class MessageGUI2 extends GUI {

	//List of users to be linked with database
	private JList users;

	//Output screen to show previous messages
	private JTextPane chat;

	//User Input
	private JTextField input;
	private JButton send;

	public MessageGUI2() throws Exception{

		//Uses GUI Superclass
		super();

		setTitle("Messages");
		setSize(1280, 720);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//This code represents the window displaying a list of friends
		users = new JList();
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
				// TODO send message to server
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
