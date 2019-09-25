/**
 * RegisterWindow.java
 * @author Dan Woolsey
 *
 * Window for registering a new user into the system
 *
 * (-) As with UserSearchBox - search fails if searchstring > 4 characters long
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;
import java.util.ArrayList;

public class RegisterWindow extends AbstractWindow
{

    private Screen s;
    private TerminalSize size;

    TextBox uname_t;
    TextBox fname_t;
    TextBox sname_t;
    TextBox mobno_t;
    TextBox bday_t;
    TextBox city_t;
    TextBox img_t;

    // for some reason cant use sep twice
    Separator sep;
    Separator sep2;

    //MessageDialog msgd = new MessageDialog("Failed")

    public RegisterWindow(Screen s)
    {
        this.s = s;
        this.setTheme();
        Panel m = new Panel(new GridLayout(1));
        m.addComponent(this.buildTitle());
        m.addComponent(this.sep);
        m.addComponent(this.buildMidPanel());
        m.addComponent(this.sep2);
        m.addComponent(this.buildBottom());
        this.setComponent(m);
    }

    public void setTheme()
    {
        this.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK, SGR.BORDERED));
        this.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
        this.sep = new Separator(Direction.HORIZONTAL);
        this.sep.setLayoutData(Layouts.SEPARATOR_HORI_LAYOUT.data);
        this.sep2 = new Separator(Direction.HORIZONTAL);
        this.sep2.setLayoutData(Layouts.SEPARATOR_HORI_LAYOUT.data);
    }

    public Label buildTitle()
    {
        //Panel top = new Panel(new GridLayout(1));
        Label title = new Label(Art.banner);
        //title.addStyle(SGR.BORDERED);
        LayoutData layout = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                        GridLayout.Alignment.CENTER,
                                                        true, false);
        title.setLayoutData(layout);
        //top.addComponent(title);
        return title;
    }

    public Panel buildMidPanel()
    {
        Panel content = new Panel(new GridLayout(2));
        content.setLayoutData(Layouts.CENTERED_LAYOUT.data);

        Label uname_l = new Label("Username");
        Label fname_l = new Label("First Name");
        Label sname_l = new Label("Surname");
        Label mobno_l = new Label("Mobile Number");
        Label bday_l = new Label("Birthday (DD/MM)");
        Label city_l = new Label("City");
        Label img_l = new Label("Image Path");

        this.uname_t = new TextBox();
        this.fname_t = new TextBox();
        this.sname_t = new TextBox();
        this.mobno_t = new TextBox();
        this.bday_t = new TextBox();
        this.city_t = new TextBox();
        this.img_t = new TextBox();

        //uname_l.addStyle(SGR.BORDERED);

        content.addComponent(uname_l);
        content.addComponent(uname_t);
        content.addComponent(fname_l);
        content.addComponent(fname_t);
        content.addComponent(sname_l);
        content.addComponent(sname_t);
        content.addComponent(mobno_l);
        content.addComponent(mobno_t);
        content.addComponent(bday_l);
        content.addComponent(bday_t);
        content.addComponent(city_l);
        content.addComponent(city_t);
        content.addComponent(img_l);
        content.addComponent(img_t);

        return content;
    }

    public Button buildBottom()
    {
        Button register = new Button("Register", new Runnable()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        String un = RegisterWindow.this.uname_t.getText();
                                                        ArrayList<Account> check = Main.tree.searchBeginningWith(un);
                                                        if(check.size() == 1)
                                                        {
                                                            MessageDialogButton b = MessageDialog.showMessageDialog(RegisterWindow.this.getTextGUI(),
                                                                                                "Failed","Username already exists",
                                                                                                MessageDialogButton.Retry);
                                                        }
                                                        else
                                                        {
                                                            String fn = RegisterWindow.this.fname_t.getText();
                                                            String sn = RegisterWindow.this.sname_t.getText();
                                                            String mn = RegisterWindow.this.mobno_t.getText();
                                                            String cn = RegisterWindow.this.city_t.getText();
                                                            String bd = RegisterWindow.this.bday_t.getText();
                                                            String ig = RegisterWindow.this.img_t.getText();
                                                            Account a = new Account(un, fn, sn, mn, bd, cn, 0, null, ig);
                                                            try
                                                            {
                                                                Main.tree.addNewAccount(a); // this will update tree, graph and the database
                                                            }
                                                            catch(Exception e)
                                                            {
                                                                e.printStackTrace();
                                                            }
                                                            // update the LoginWindows accounts list
                                                            LoginWindow x = (LoginWindow) RegisterWindow.this.getTextGUI().getWindows().toArray()[0];
                                                            x.accs.add(a);
                                                            // refresh the tree and graph
                                                            Main.refresh();
                                                            // go back to login screen
                                                            RegisterWindow.this.close();
                                                        }
                                                    }
                                                });
        register.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        return register;
    }
}
