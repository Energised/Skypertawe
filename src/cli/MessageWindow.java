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

public class MessageWindow extends AbstractWindow
{

    TextBox log;

    public MessageWindow(Screen s, Account ac)
    {
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

    public Panel buildLeftPanel()
    {
        Panel l = new Panel(new GridLayout(1));

        Label b = new Label(Art.sbanner);

        Separator s = new Separator(Direction.HORIZONTAL);
        s.setLayoutData(Layouts.FILLED_LAYOUT.data);

        ActionListBox flist = new ActionListBox();
        //RadioBoxList flist = new RadioBoxList();

        //flist.addItem("aaa");
        //flist.addItem("bbb");
        //flist.addItem("ccc");

        flist.addItem("aaa", new Runnable(){
            @Override
            public void run()
            {
                MessageWindow.this.log.setText("showing messages for aaa");
            }
        });
        flist.addItem("bbb", new Runnable(){
            @Override
            public void run()
            {
                MessageWindow.this.log.setText("showing messages for bbb");
            }
        });
        flist.addItem("ccc", new Runnable(){
            @Override
            public void run()
            {
                MessageWindow.this.log.setText("showing messages for ccc");
            }
        });

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
        l.addComponent(flist);
        l.addComponent(s1);
        l.addComponent(back);

        return l;
    }

    public Panel buildRightPanel()
    {
        Panel r = new Panel(new GridLayout(1));

        this.log = new TextBox("Select a user to message");
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

        return r;
    }
}
