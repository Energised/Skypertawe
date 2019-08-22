/**
 * Home.java
 * @author Dan Woolsey
 *
 * CLI homepage after login for Skypertawe
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;

import com.googlecode.lanterna.gui2.*;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Arrays;
import java.util.ArrayList;

public class Home
{

    private Account acc;

    final String banner1 = "   _____ __                         __                    \n";
    final String banner2 = "  / ___// /____  ______  ___  _____/ /_____ __      _____ \n";
    final String banner3 = "  \\__ \\/ //_/ / / / __ \\/ _ \\/ ___/ __/ __ `/ | /| / / _ \\\n";
    final String banner4 = " ___/ / ,< / /_/ / /_/ /  __/ /  / /_/ /_/ /| |/ |/ /  __/\n";
    final String banner5 = "/____/_/|_|\\__, / .___/\\___/_/   \\__/\\__,_/ |__/|__/\\___/ \n";
    final String banner6 = "          /____/_/                                        \n";

    final String banner = banner1 + banner2 + banner3 + banner4 + banner5 + banner6;

    private Terminal terminal;
    private Screen screen;
    private BasicWindow window;

    public Home(Account acc)
    {
        this.acc = acc;
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        this.screen = null;

        Separator sep = new Separator(Direction.VERTICAL);
        sep.setLayoutData(Layouts.SEPARATOR_VERT_LAYOUT.data);

        try
        {
            this.terminal = dtf.createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            this.screen.setCursorPosition(null);

            this.screen.startScreen();

            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(this.screen);

            BasicWindow window = new BasicWindow();
            this.window = window;

            this.window.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
            this.window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));

            //Panel contentPanel = new Panel(new LinearLayout(Direction.VERTICAL));
            Panel contentPanel = new Panel(new GridLayout(1));
            Panel topPanel = new Panel(new GridLayout(3));
            Panel bottomPanel = this.buildBottomPanel();
            Panel topLeftPanel = this.buildTopLeftPanel();
            Panel topRightPanel = this.buildTopRightPanel();

            topPanel.addComponent(topLeftPanel);
            topPanel.addComponent(sep);
            topPanel.addComponent(topRightPanel);
            // NB: not sure why its in this position but will try to handle later
            //topPanel.addComponent(new EmptySpace(TextColor.ANSI.WHITE));

            Separator mainSep = new Separator(Direction.HORIZONTAL);
            mainSep.setLayoutData(Layouts.SEPARATOR_HORI_LAYOUT.data);

            contentPanel.addComponent(topPanel);
            contentPanel.addComponent(mainSep);
            contentPanel.addComponent(bottomPanel);

            this.window.setComponent(contentPanel);
            textGUI.addWindowAndWait(window);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(screen != null)
            {
                try
                {
                    screen.stopScreen();
                    // fixes no echo on exit
                    // NB: find fix that's better than this
                    Runtime r = Runtime.getRuntime();
                    Process p = r.exec("reset");
                    p.waitFor();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public Panel buildTopLeftPanel()
    {
        Panel tlp = new Panel(new GridLayout(1).setVerticalSpacing(1));

        Label b = new Label(banner);
        Label fl = new Label("~ Friends List ~");
        Label fr = new Label("~ Friend Requests ~");

        // insert names of friends
        ArrayList<Account> friends = Main.graph.getFriends(acc);
        ArrayList<String> fnames = new ArrayList<String>();
        for(Account a : friends)
        {
            fnames.add(a.getUsername());
        }

        ComboBox<String> flb = new ComboBox<String>(fnames);
        flb.setReadOnly(true);
        flb.setPreferredSize(new TerminalSize(20, 3)); // only using for the vertical fill (3)

        // insert names of friend requests
        ArrayList<Account> requests = Main.graph.getRequests(acc);
        ArrayList<String> rnames = new ArrayList<String>();
        for(Account a : requests)
        {
            rnames.add(a.getUsername());
        }

        ComboBox<String> frb = new ComboBox<String>(rnames);
        frb.setReadOnly(true);
        frb.setPreferredSize(new TerminalSize(20, 3)); // as above

        // insert new record into graph
        // update graph
        // remove item from list
        Button acceptRequest = new Button("Accept Selected Request", new Runnable()
        {
            @Override
            public void run()
            {
                frb.removeItem(frb.getSelectedItem());
            }
        });

        fl.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        fr.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        flb.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        frb.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        acceptRequest.setLayoutData(Layouts.CENTERED_LAYOUT.data);

        tlp.addComponent(b);
        tlp.addComponent(fl);
        tlp.addComponent(flb);
        tlp.addComponent(fr);
        tlp.addComponent(frb);
        tlp.addComponent(acceptRequest);

        return tlp;
    }

    public Panel buildTopRightPanel()
    {
        Panel trp = new Panel(new LinearLayout());

        Label uname = new Label(acc.getUsername());
        Label fname = new Label(acc.getFirstName() + " " + acc.getSurname());
        Label mobNo = new Label(acc.getMobnumber());
        Label bday = new Label(acc.getBirthDate());
        Label city = new Label(acc.getCity());

        Label searchLabel = new Label("~ Search all users ~");

        UserSearchBox searchBox = new UserSearchBox(acc);

        uname.addStyle(SGR.BOLD);
        uname.addStyle(SGR.UNDERLINE);

        fname.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.End));
        //mobNo.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        //bday.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        //city.setLayoutData(Layouts.CENTERED_LAYOUT.data);

        trp.addComponent(uname);
        trp.addComponent(new EmptySpace());
        trp.addComponent(fname);
        trp.addComponent(mobNo);
        trp.addComponent(bday);
        trp.addComponent(city);
        trp.addComponent(new EmptySpace());
        trp.addComponent(searchLabel);
        trp.addComponent(searchBox);
        // has to be a reference to the CheckBoxList in searchBox
        trp.addComponent(searchBox.searchedUsers);

        return trp;
    }

    public Panel buildBottomPanel()
    {
        Panel bp = new Panel(new GridLayout(3).setHorizontalSpacing(40));
        // should change the spacing to terminal length

        Button msg = new Button("Messages", new Runnable()
        {
            @Override
            public void run()
            {
                // switch to Messages window
            }
        });

        Button edit = new Button("Edit", new Runnable()
        {
            @Override
            public void run()
            {
                // switch to edit account window
            }
        });

        Button close = new Button("Close", new Runnable(){
            @Override
            public void run()
            {
                window.close();
            }
        });

        Panel lButton = new Panel(new GridLayout(1));
        Panel mButton = new Panel(new GridLayout(1));
        Panel rButton = new Panel(new GridLayout(1));

        lButton.addComponent(msg);
        mButton.addComponent(edit);
        rButton.addComponent(close);

        bp.addComponent(lButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);
        bp.addComponent(mButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);
        bp.addComponent(rButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);

        return bp;
    }

    public static void main(String[] args)
    {
        Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
        Home h = new Home(ac1);
    }
}
