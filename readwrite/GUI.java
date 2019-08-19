/**
* GUI.java
* @author Carl Thomas, Dan Woolsey
*
* Base class for all GUIs
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class GUI extends JFrame {

    private Main m;

    JMenuItem homeAction;
    JMenuItem profileAction;
    JMenuItem messageAction;
    JMenuItem drawAction;
    JMenuItem logoutAction;

    /**
    * Generates a screen and a toolbar to be used by its subclasses
    */

    public GUI() throws Exception{

        setTitle("Skypertawe");
        setSize(1280, 720);

        // reference to the main class to use BST and Graph
        this.m = new Main();

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
                    get_main().get_login();
                    dispose();
                }
                catch(Exception f)
                {
                    System.out.println(f);
                }
            }
        });
    }

    /**
    * @return reference to the main file
    */

    public Main get_main() throws Exception
    {
        return this.m;
    }

    /**
    * Resets the Main class to refresh BST and Graph
    */

    public Main reset_main() throws Exception
    {
        this.m = new Main();
        return this.m;
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
