/**
 * LoginGUI.java
 * @author Dan Woolsey
 *
 * Login screen that runs on startup
 *
 *  TODO: Implement Register button ActionListener
 *        Complete Login button ActionListener
 *
 */

package src.gui;

import src.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.*;

public class LoginGUI extends GUI
{

    public LoginGUI() throws Exception
    {
        super();
        setSize(400,200);
        buildLogin();
    }

    public void buildLogin() throws Exception
    {
        JPanel screen = new JPanel();
        add(screen);
        screen.setLayout(new BorderLayout(20,30));

        JLabel titleLabel = new JLabel("Skypertawe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 32));
        screen.add(titleLabel, BorderLayout.NORTH);

        JLabel userLabel = new JLabel("Username: ");
        screen.add(userLabel, BorderLayout.WEST);

        JTextField userText = new JTextField(20);
        screen.add(userText, BorderLayout.CENTER);

        JButton login = new JButton("Login");
        screen.add(login, BorderLayout.EAST);

        login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String username = userText.getText();
                System.out.println(username);
            }
        });

        JButton register = new JButton("Register");
        screen.add(register, BorderLayout.SOUTH);

    }

    public static void main(String[] args) throws Exception
    {
        LoginGUI l = new LoginGUI();
        l.setVisible(true);
    }
}
