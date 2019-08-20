/**
 * Home.java
 * @author Dan Woolsey
 *
 * CLI homepage after login for Skypertawe
 */

package src.cli;

import src.obj.*;
import src.Main;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.TextColor;

import java.util.Arrays;
import java.util.ArrayList;

import java.io.IOException;

public class Home
{

 final String banner1 = "   _____ __                         __                    ";
 final String banner2 = "  / ___// /____  ______  ___  _____/ /_____ __      _____ ";
 final String banner3 = "  \\__ \\/ //_/ / / / __ \\/ _ \\/ ___/ __/ __ `/ | /| / / _ \\";
 final String banner4 = " ___/ / ,< / /_/ / /_/ /  __/ /  / /_/ /_/ /| |/ |/ /  __/";
 final String banner5 = "/____/_/|_|\\__, / .___/\\___/_/   \\__/\\__,_/ |__/|__/\\___/ ";
 final String banner6 = "          /____/_/                                        ";

 public Home(Account a)
 {
     DefaultTerminalFactory dtf = new DefaultTerminalFactory();
     // doesnt help but gonna keep for now
     dtf.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
     Screen screen = null;

     try
     {
        Terminal terminal = dtf.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.startScreen();
        //screen.setCursorPosition(null);

        // get our size here, use getColumns() / getRows()
        TerminalSize terminalSize = screen.getTerminalSize();

        // terminal resize condition
        TerminalSize newSize = screen.doResizeIfNecessary();
        if(newSize != null)
        {
            terminalSize = newSize;
        }

        // window manager and window setup
        WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen, TextColor.ANSI.BLACK);
        Window window = new BasicWindow();

        // theme
        window.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));

        // cheeky window hints are lovely
        window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));

        // panel placed inside window
        Panel contentPanel = new Panel(new GridLayout(3));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setVerticalSpacing(2);
        gridLayout.setHorizontalSpacing(2);

        Label title = new Label(banner1 + "\n" + banner2 + "\n" +
                                banner3 + "\n" + banner4 + "\n" +
                                banner5 + "\n" + banner6);
        title.setForegroundColor(TextColor.ANSI.GREEN);
        title.setBackgroundColor(TextColor.ANSI.BLACK);

        title.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING,
                                                        GridLayout.Alignment.BEGINNING,
                                                        false,
                                                        false,
                                                        1,
                                                        6));

        contentPanel.addComponent(title);

        // seperator for rhs
        Separator sep = new Separator(Direction.VERTICAL);
        sep.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                      GridLayout.Alignment.FILL,
                                                      false,
                                                      true));
        contentPanel.addComponent(sep);

        String userInfo = a.getUsername() + "\n" + a.getFirstName() + " " +
                          a.getSurname() + "\n\n" + a.getMobnumber() + "\n" +
                          a.getBirthDate() + "\n" + a.getCity() + "\n" +
                          a.getImgPath();
        Label user = new Label(userInfo);
        user.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                       GridLayout.Alignment.FILL,
                                                       false,
                                                       true));
        contentPanel.addComponent(user);

        Label friendsTitle = new Label("~ Friends List ~");
        friendsTitle.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING,
                                                               GridLayout.Alignment.BEGINNING,
                                                               false,
                                                               false,
                                                               1,
                                                               1));
        contentPanel.addComponent(friendsTitle);

        ArrayList<Account> friendsList = Main.graph.getFriends(a);
        String listFriends = "";
        for(Account acc : friendsList)
        {
            listFriends = listFriends + acc.getUsername() + "\n";
        }
        Label l = new Label(listFriends);
        contentPanel.addComponent(l);

        Button close = new Button("Close", new Runnable()
                       {
                           @Override
                           public void run()
                           {
                               window.close();
                           }
                        });
        contentPanel.addComponent(close);

        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
     }
     catch(IOException e)
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
}
