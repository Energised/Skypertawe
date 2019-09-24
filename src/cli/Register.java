/**
 * Register.java
 * @author Dan Woolsey
 *
 * Register page accessed via Login on startup
 * Takes an ArrayList<Account> to check for repeat usernames - username must be unique
 */

package src.cli;

import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.swing.*;

import java.util.Arrays;
import java.util.ArrayList;

public class Register
{

    private Terminal terminal;
    private Screen screen;
    private BasicWindow window;

    final String banner1 = "   _____ __                         __                    \n";
    final String banner2 = "  / ___// /____  ______  ___  _____/ /_____ __      _____ \n";
    final String banner3 = "  \\__ \\/ //_/ / / / __ \\/ _ \\/ ___/ __/ __ `/ | /| / / _ \\\n";
    final String banner4 = " ___/ / ,< / /_/ / /_/ /  __/ /  / /_/ /_/ /| |/ |/ /  __/\n";
    final String banner5 = "/____/_/|_|\\__, / .___/\\___/_/   \\__/\\__,_/ |__/|__/\\___/ \n";
    final String banner6 = "          /____/_/                                        \n";

    final String banner = banner1 + banner2 + banner3 + banner4 + banner5 + banner6;

    public Register(ArrayList<Account> accs)
    {
        // basic startup info
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        // doesnt fix but helped so gonna keep for now
        dtf.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
        this.screen = null;

        try
        {
            this.terminal = dtf.createTerminal();
            this.screen = new TerminalScreen(this.terminal);

            this.screen.startScreen();
            this.screen.setCursorPosition(null);

            // get our size here, use getColumns() / getRows()
            TerminalSize terminalSize = this.screen.getTerminalSize();


            // terminal resize condition
            TerminalSize newSize = this.screen.doResizeIfNecessary();
            if(newSize != null)
            {
                terminalSize = newSize;
            }

            // window manager and window setup
            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(this.screen, TextColor.ANSI.BLACK);
            this.window = new BasicWindow();

            // theme
            this.window.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));

            // cheeky window hints are lovely
            this.window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));

            // setting up panels function calls
            Panel contentPanel = new Panel(new GridLayout(1));
            //Panel top = this.buildTopPanel();

            Label b = new Label(banner);
            b.setLayoutData(Layouts.TITLE_LAYOUT.data);

            Panel mid = this.buildMiddlePanel();


            contentPanel.addComponent(b);

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
                    //Runtime r = Runtime.getRuntime();
                    //Process p = r.exec("reset");
                    //p.waitFor();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public Panel buildMiddlePanel()
    {
        return new Panel();
    }

    public static void main(String[] args)
    {
        ArrayList<Account> a = new ArrayList<Account>();
        Register r = new Register(a);
    }
}
