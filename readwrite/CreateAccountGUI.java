/**
* CreateAccountGUI.java
* @author Dan Woolsey
*
* Screen to take new Account information and create an Account object
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateAccountGUI extends GUI
{
    public CreateAccountGUI() throws Exception
    {
        super();

        JPanel panel = new JPanel();
        add(panel);

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel firstnameLabel = new JLabel("First Name: ");
        JLabel surnameLabel = new JLabel("Surname: ");
        JLabel mobnumLabel = new JLabel("Mobile No: ");
        JLabel dobLabel = new JLabel("Date of Birth: ");
        JLabel cityLabel = new JLabel("City: ");
        usernameLabel.setBounds(10,50,100,60);
        panel.add(usernameLabel);
    }

    public static void main(String[] args) throws Exception
    {
        CreateAccountGUI c = new CreateAccountGUI();
        c.setVisible(true);
    }
}
