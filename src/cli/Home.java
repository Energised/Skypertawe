/**
 * Home.java
 * @author Dan Woolsey
 *
 * CLI homepage after login for Skypertawe
 */

package src.cli;

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

    final String banner1 = "   _____ __                         __                    \n";
    final String banner2 = "  / ___// /____  ______  ___  _____/ /_____ __      _____ \n";
    final String banner3 = "  \\__ \\/ //_/ / / / __ \\/ _ \\/ ___/ __/ __ `/ | /| / / _ \\\n";
    final String banner4 = " ___/ / ,< / /_/ / /_/ /  __/ /  / /_/ /_/ /| |/ |/ /  __/\n";
    final String banner5 = "/____/_/|_|\\__, / .___/\\___/_/   \\__/\\__,_/ |__/|__/\\___/ \n";
    final String banner6 = "          /____/_/                                        \n";

    final String banner = banner1 + banner2 + banner3 + banner4 + banner5 + banner6;

    ArrayList<String> names = new ArrayList<String>();

    private Terminal terminal;
    private Screen screen;

    public LayoutData centeredLayout = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                                   GridLayout.Alignment.CENTER,
                                                                   false, false, 1, 1);

    public LayoutData filledLayout = GridLayout.createLayoutData(GridLayout.Alignment.FILL,
                                                                 GridLayout.Alignment.FILL,
                                                                 true, true, 1, 1);


    public Home(Account acc)
    {
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        this.screen = null;

        this.names.add("gman");
        this.names.add("shutup");
        this.names.add("poopface");

        Separator sep = new Separator(Direction.VERTICAL);
        sep.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                      GridLayout.Alignment.FILL,
                                                      false,
                                                      true));

        try
        {
            this.terminal = dtf.createTerminal();
            this.screen = new TerminalScreen(this.terminal);

            this.screen.startScreen();

            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(this.screen);

            final BasicWindow window = new BasicWindow();

            window.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
            window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));

            Panel contentPanel = new Panel(new LinearLayout(Direction.VERTICAL));
            Panel topPanel = new Panel(new GridLayout(3));
            Panel bottomPanel = new Panel(new GridLayout(3));
            Panel topLeftPanel = this.buildTopLeftPanel();
            Panel topRightPanel = new Panel(new LinearLayout(Direction.VERTICAL));

            topRightPanel.addComponent(new EmptySpace(TextColor.ANSI.GREEN));

            topPanel.addComponent(topLeftPanel, centeredLayout);
            topPanel.addComponent(sep);
            topPanel.addComponent(topRightPanel, centeredLayout);

            bottomPanel.addComponent(new Button("Close", new Runnable() {
                @Override
                public void run() {
                    window.close();
                }
            }));

            contentPanel.addComponent(topPanel);
            contentPanel.addComponent(bottomPanel);

            window.setComponent(contentPanel);
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

        // names is placeholder for info taken from Graph
        ComboBox<String> flb = new ComboBox<String>(names);
        flb.setReadOnly(true);
        flb.setPreferredSize(new TerminalSize(10, 3)); // only using for the vertical fill (4)

        ComboBox<String> frb = new ComboBox<String>(names);
        frb.setReadOnly(true);
        frb.setPreferredSize(new TerminalSize(10, 3)); // as above

        Button acceptRequest = new Button("Accept Selected Request", new Runnable()
        {
            @Override
            public void run()
            {
                frb.removeItem(frb.getSelectedItem());
            }
        });

        fl.setLayoutData(centeredLayout);
        fr.setLayoutData(centeredLayout);
        flb.setLayoutData(filledLayout);
        frb.setLayoutData(filledLayout);
        acceptRequest.setLayoutData(centeredLayout);

        tlp.addComponent(b);
        tlp.addComponent(fl);
        tlp.addComponent(flb);
        tlp.addComponent(fr);
        tlp.addComponent(frb);
        tlp.addComponent(acceptRequest);

        return tlp;
    }

    public static void main(String[] args)
    {
        Home h = new Home(new Account());
    }
}
