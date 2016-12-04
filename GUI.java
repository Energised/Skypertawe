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
     
    public GUI() {
         
        setTitle("Skypertawe");
        setSize(1280, 720);
         
        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Add the menubar to the frame
        setJMenuBar(menuBar);
         
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
         
        // Create and add simple menu item to one of the drop down menu
        JMenuItem homeAction = new JMenuItem("Home");
        JMenuItem profileAction = new JMenuItem("Profile");
        JMenuItem friendAction = new JMenuItem("Friends");
        JMenuItem messageAction = new JMenuItem("Messages");
        JMenuItem drawAction = new JMenuItem("Draw");
        JMenuItem exitAction = new JMenuItem("Log Out");
                 
        fileMenu.add(homeAction);
        fileMenu.add(profileAction);
        fileMenu.add(friendAction);
        fileMenu.add(messageAction);
        fileMenu.add(drawAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggered this menu item
        homeAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Going Home");
            }
        });
    }
    public static void main(String[] args) {
        GUI me = new GUI();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
    }
}