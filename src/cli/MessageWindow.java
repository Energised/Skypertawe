/**
 * MessageWindow.java
 * @author Dan Woolsey
 *
 * Interface for the messaging system
 *
 * NB: ActionListBox makes more sense but is less aesthetically pleasing imo
 *     RadioListBox could work but would require subclassing and dealing with handleKeyStroke()
 */

package src.cli;

import src.*;
import src.obj.*;
import src.cli.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageWindow extends AbstractWindow
{

    TextBox log;

    Account ac;
    //ActionListBox flist;
    RadioBoxList flistr;

    ArrayList<Account> friends;

    public MessageWindow(Screen s, Account ac) throws Exception
    {
        this.ac = ac;
        this.log = new TextBox(new TerminalSize(40,15), TextBox.Style.MULTI_LINE); // hardcoded for now
        this.setTheme();
        Panel main = new Panel(new GridLayout(3));
        main.addComponent(this.buildLeftPanel());
        main.addComponent(new Separator(Direction.VERTICAL).setLayoutData(Layouts.VERT_FILL.data));
        main.addComponent(this.buildRightPanel());
        this.setComponent(main);
    }

    public void setTheme()
    {
        this.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
        this.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
    }

    public Panel buildLeftPanel() throws Exception
    {
        Panel l = new Panel(new GridLayout(1));

        Label b = new Label(Art.sbanner);

        Separator s = new Separator(Direction.HORIZONTAL);
        s.setLayoutData(Layouts.FILLED_LAYOUT.data);

        //this.flist = new ActionListBox();
        this.flistr = new RadioBoxList();

        // need list of users friends
        friends = Main.graph.getFriends(ac);

        for(Account a : friends)
        {
            this.flistr.addItem(a);
        }

        RadioBoxList.Listener listen = new LogListener(this.flistr, this.log, ac);

        this.flistr.addListener(listen);

        Separator s1 = new Separator(Direction.HORIZONTAL);
        s1.setLayoutData(Layouts.FILLED_LAYOUT.data);

        Button back = new Button("Home", new Runnable(){
            @Override
            public void run()
            {
                MessageWindow.this.close();
            }
        });
        back.setLayoutData(Layouts.CENTERED_LAYOUT);

        l.addComponent(b);
        l.addComponent(s);
        l.addComponent(this.flistr);
        l.addComponent(s1);
        l.addComponent(back);

        return l;
    }

    public Panel buildRightPanel()
    {
        Panel r = new Panel(new GridLayout(1));

        //this.log = new TextBox("Select a user to message");
        this.log.setReadOnly(true);

        TextBox msg = new TextBox();
        Button send = new Button("Send", new Runnable(){
            @Override
            public void run()
            {
                String msgtext = msg.getText();
            }
        });

        r.addComponent(this.log);
        r.addComponent(msg);
        r.addComponent(send);

        return r;
    }
}
