/**
 * LoginWindow.java
 * @author Dan Woolsey
 *
 * Subclassing AbstractWindow to restructure CLI
 */

package src.cli;

import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;
import java.util.ArrayList;

public class LoginWindow extends AbstractWindow
{

    private Screen s;
    private TerminalSize size;

    ArrayList<Account> accs;

    public LoginWindow(Screen s, ArrayList<Account> accs)
    {
        this.s = s;
        this.accs = accs;
        this.size = s.getTerminalSize();
        this.setTheme();
        Panel content = this.buildMainPanel();
        this.setComponent(content);
    }

    public void setTheme()
    {
        this.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
        this.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
    }

    public Panel buildMainPanel()
    {
        Panel main = new Panel(new GridLayout(1).setVerticalSpacing(1));
        // create default layout data
        LayoutData layout = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                        GridLayout.Alignment.CENTER,
                                                        true,
                                                        false);

        // place title in centered position
        Label title = new Label(Art.banner);
        title.setForegroundColor(TextColor.ANSI.GREEN);
        title.setBackgroundColor(TextColor.ANSI.BLACK);

        title.setLayoutData(layout);

        main.addComponent(title);

        TextBox nameBox = new TextBox();
        nameBox.withBorder(Borders.doubleLine());
        nameBox.setLayoutData(layout);
        main.addComponent(nameBox);

        Button login = new Button("Login", new Runnable()
                       {
                           @Override
                           public void run()
                           {
                               String username = nameBox.getText();
                               try
                               {
                                   for(Account a : accs)
                                   {
                                       if(a.getUsername().equals(username))
                                       {
                                           // order here is awkward, decide on how best to cycle to new window
                                           // could prebuild all windows in SubclassTest and switch there?
                                           HomeWindow h = new HomeWindow(s, a);
                                           //LoginWindow.this.close();
                                           LoginWindow.this.getTextGUI().addWindowAndWait(h);
                                           //LoginWindow.this.getTextGUI().removeWindow(LoginWindow.this);
                                       }
                                   }
                               }
                               catch(Exception e)
                               {
                                   e.printStackTrace();
                               }
                           }
                       });
        login.setLayoutData(layout);
        main.addComponent(login);

        Button register = new Button("Register", new Runnable()
                          {
                              @Override
                              public void run()
                              {
                                  RegisterWindow r = new RegisterWindow(s);
                                  LoginWindow.this.getTextGUI().addWindowAndWait(r);
                              }
                          });
        register.setLayoutData(layout);
        main.addComponent(register);

        Button close = new Button("Close", new Runnable()
                       {
                           @Override
                           public void run()
                           {
                               // best way to close a window
                               LoginWindow.this.close();
                           }
                       });
        close.setLayoutData(layout);
        main.addComponent(close);

        return main;
    }
}
