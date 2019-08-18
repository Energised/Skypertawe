/**
 * Login.java
 * @author Dan Woolsey
 *
 * Lanterna interface for Skypertawe program
 * NB: This could be a really fun idea or a really painful idea
 *
 * To fully compile with both lanterna and sqlite jars, use:
 *      javac -cp .:sqlite-jdbc-3.15.1.jar:lanterna-3.0.1.jar LoginMultiTest.java
 *      java -cp .:sqlite-jdbc-3.15.1.jar:lanterna-3.0.1.jar LoginMultiTest
 *
 */

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginMultiTest
{

    final String banner1 = "   _____ __                         __                    ";
    final String banner2 = "  / ___// /____  ______  ___  _____/ /_____ __      _____ ";
    final String banner3 = "  \\__ \\/ //_/ / / / __ \\/ _ \\/ ___/ __/ __ `/ | /| / / _ \\";
    final String banner4 = " ___/ / ,< / /_/ / /_/ /  __/ /  / /_/ /_/ /| |/ |/ /  __/";
    final String banner5 = "/____/_/|_|\\__, / .___/\\___/_/   \\__/\\__,_/ |__/|__/\\___/ ";
    final String banner6 = "          /____/_/                                        ";

    public LoginMultiTest()
    {
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        Screen screen = null;

        try
        {
            Terminal terminal = dtf.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.setCursorPosition(null);

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
            Panel contentPanel = new Panel(new GridLayout(1));
            GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
            gridLayout.setVerticalSpacing(3);

            // create default layout data
            LayoutData layout = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                            GridLayout.Alignment.CENTER,
                                                            true,
                                                            false);

            // place title in centered position
            Label title = new Label(banner1 + "\n" + banner2 + "\n" +
                                    banner3 + "\n" + banner4 + "\n" +
                                    banner5 + "\n" + banner6);
            title.setForegroundColor(TextColor.ANSI.GREEN);
            title.setBackgroundColor(TextColor.ANSI.BLACK);

            title.setLayoutData(layout);
            contentPanel.addComponent(title);

            // place TextBox for username entry
            // NB: Can't get border to work, will try later on
            TextBox nameBox = new TextBox(new TerminalSize(20,1));
            nameBox.withBorder(Borders.doubleLine());
            nameBox.setLayoutData(layout);
            contentPanel.addComponent(nameBox);

            Button login = new Button("Login", new Runnable()
                           {
                               @Override
                               public void run()
                               {
                                   String username = nameBox.getText();
                                   // will then switch to Home screen
                               }
                           });
            login.setLayoutData(layout);
            contentPanel.addComponent(login);

            Button register = new Button("Register", new Runnable()
                              {
                                  @Override
                                  public void run()
                                  {
                                      // will then switch to CreateAccount screen
                                  }
                              });
            register.setLayoutData(layout);
            contentPanel.addComponent(register);

            Button close = new Button("Close", new Runnable()
                           {
                               @Override
                               public void run()
                               {
                                   window.close();
                               }
                           });
            close.setLayoutData(layout);
            contentPanel.addComponent(close);

            // final setup
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
                    screen.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        LoginMultiTest l = new LoginMultiTest();
    }
}