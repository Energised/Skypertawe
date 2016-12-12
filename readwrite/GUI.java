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
    JMenuItem exitAction;

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
        this.exitAction = new JMenuItem("Log Out");

        fileMenu.add(this.homeAction);
        fileMenu.add(this.profileAction);
        fileMenu.add(this.messageAction);
        fileMenu.add(this.drawAction);
        fileMenu.addSeparator();
        fileMenu.add(this.exitAction);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggered this menu item

        this.exitAction.addActionListener(new ActionListener(){
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

    public Main get_main() throws Exception
    {
        return this.m;
    }

    public static void main(String[] args) throws Exception {
       GUI me = new GUI();
       //me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       me.setVisible(true);
    }
}
