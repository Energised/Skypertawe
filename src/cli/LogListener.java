/**
 * LogListener.java
 * @author Dan Woolsey
 *
 * Implementing the RadioBoxList.Listener interface for use in MessageWindow
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LogListener implements RadioBoxList.Listener
{

    public RadioBoxList rbl;
    public TextBox logBox;
    public Account ac; // user currently logged in
    public HashMap<String, ArrayList<Message>> org_msgs;

    public LogListener(RadioBoxList rbl, TextBox logBox, Account ac) throws Exception
    {
        this.rbl = rbl;
        this.logBox = logBox;
        this.ac = ac;
        this.org_msgs = this.setupMessages();
    }

    public void onSelectionChanged(int selectedIndex, int previousSelection)
    {
        Account a = (Account) this.rbl.getItemAt(selectedIndex); // get chosen account
        ArrayList<Message> a_msgs = this.org_msgs.get(a.getUsername());
        String msgs_string = "";
        if(a_msgs != null)
        {
            for(Message m : a_msgs)
            {
                if(m.getClass().equals(src.obj.TextMessage.class))
                {
                    TextMessage txt = (TextMessage) m;
                    msgs_string += "Sender: " + txt.getSender() + "\nContent: " +
                                    txt.getMessageContent() + "\nSent at: " + txt.getDateTime();
                }
                else if(m.getClass().equals(src.obj.URLMessage.class))
                {
                    URLMessage url = (URLMessage) m;
                    msgs_string += "Sender: " + url.getSender() + "\nContent: " +
                                    url.getURL() + "\nSent at: " + url.getDateTime();
                }
                msgs_string += "\n~~~~~~~~~~~\n";
                //msgs_string += m.getClass().toString();
            }
        }
        else
        {
            msgs_string = "no messages";
        }
        this.logBox.setText(msgs_string);
    }

    public HashMap<String, ArrayList<Message>> setupMessages() throws Exception
    {
        ArrayList<Message> msgs = Main.postie.getMessagesByUser(this.ac.getUsername());
        HashMap<String, ArrayList<Message>> org_msgs = Main.postie.sortUserPostByConvo(this.ac, msgs);
        return org_msgs;
    }
}
