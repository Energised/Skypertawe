/**
 * GUI.java
 * @author Dan Woolsey
 *
 * Base class for all GUI classes to preserve menu bar
 */

package src.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class GUI extends JFrame
{

    JMenuItem homeAction;
    JMenuItem profileAction;
    JMenuItem messageAction;
    JMenuItem drawAction;
    JMenuItem logoutAction;

    /**
    * Generates a screen and a toolbar to be used by its subclasses
    */

    public GUI() throws Exception
    {

        setTitle("Skypertawe");
        setSize(600, 200);

        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();

        // Add the menubar to the frame
        setJMenuBar(menuBar);

        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("Options");
        menuBar.add(fileMenu);

        // Create and add simple menu item to one of the drop down menu
        this.homeAction = new JMenuItem("Home");
        this.profileAction = new JMenuItem("Profile");
        this.messageAction = new JMenuItem("Messages");
        this.drawAction = new JMenuItem("Draw");
        this.logoutAction = new JMenuItem("Log Out");

        fileMenu.add(this.homeAction);
        fileMenu.add(this.profileAction);
        fileMenu.add(this.messageAction);
        fileMenu.add(this.drawAction);
        fileMenu.addSeparator();
        fileMenu.add(this.logoutAction);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggered this menu item

        this.logoutAction.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    System.exit(0); // NB: update to login screen
                }
                catch(Exception f)
                {
                    System.out.println(f);
                }
            }
        });
    }

    /**
    * Implemented for testing purposes
    */

    public static void main(String[] args) throws Exception {
       GUI me = new GUI();
       //me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       me.setVisible(true);
    }
}
