/**
 * EditWindow.java
 * @author Dan Woolsey
 *
 * EditWindow class to be accessed via HomeWindow
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;

public class EditWindow extends AbstractWindow
{

    private Screen s;

    public Account a;

    TextBox uname_t;
    TextBox fname_t;
    TextBox sname_t;
    TextBox mobno_t;
    TextBox bday_t;
    TextBox city_t;
    TextBox img_t;

    Separator sep;
    Separator sep2;

    public EditWindow(Screen s, Account a)
    {
        this.s = s;
        this.a = a;
        this.setTheme();
        Panel m = new Panel(new GridLayout(1));
        m.addComponent(this.buildTitle());
        m.addComponent(this.sep);
        m.addComponent(this.buildMainPanel());
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
        Label top = new Label(Art.sbanner);
        LayoutData layout = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                        GridLayout.Alignment.CENTER,
                                                        true, false);
        top.setLayoutData(layout);
        return top;
    }

    public Panel buildMainPanel()
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

        this.uname_t = new TextBox(this.a.getUsername()).setReadOnly(true);
        this.fname_t = new TextBox(this.a.getFirstName());
        this.sname_t = new TextBox(this.a.getSurname());
        this.mobno_t = new TextBox(this.a.getMobnumber());
        this.bday_t = new TextBox(this.a.getBirthDate());
        this.city_t = new TextBox(this.a.getCity());
        this.img_t = new TextBox(this.a.getImgPath());

        //uname_l.addStyle(SGR.BORDERED);

        content.addComponent(uname_l);
        content.addComponent(this.uname_t);
        content.addComponent(fname_l);
        content.addComponent(this.fname_t);
        content.addComponent(sname_l);
        content.addComponent(this.sname_t);
        content.addComponent(mobno_l);
        content.addComponent(this.mobno_t);
        content.addComponent(bday_l);
        content.addComponent(this.bday_t);
        content.addComponent(city_l);
        content.addComponent(this.city_t);
        content.addComponent(img_l);
        content.addComponent(this.img_t);

        return content;
    }

    public Button buildBottom()
    {
        Button save = new Button("Save Changes", new Runnable(){
            @Override
            public void run()
            {
                Account new_a = new Account(EditWindow.this.uname_t.getText(),
                                            EditWindow.this.fname_t.getText(),
                                            EditWindow.this.sname_t.getText(),
                                            EditWindow.this.mobno_t.getText(),
                                            EditWindow.this.bday_t.getText(),
                                            EditWindow.this.city_t.getText(),
                                            0, null,
                                            EditWindow.this.img_t.getText());
                try
                {
                    Main.tree.updateAccount(new_a);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                // make the changes to the account via BST
                // refresh main
                EditWindow.this.close();
            }
        });
        save.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        return save;
    }
}
