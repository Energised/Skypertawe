/**
* ReadWriteMessage.java
* @author Dan Woolsey
*
* Handles storing of sent Message objects into a text file and
* passing them to MessageGUI when a user logs in
*
* WRITING MESSAGES:
*   -> when a message is sent
*         call the method related to what Message type you're sending
*         store its data in the text file in the forms:
*
*              <recipient>,;`<type_of_message>,;`<sender>,;`<message>,;`(<url>|<filename>)(,;`)
*
*               TextMessage -> recipient,;`t,;`sender,;`message (t for text)
*               URLMessage  -> recipient,;`u,;`sender,;`message,;`url (u for url)
*               FileMessage -> recipient,;`f,;`sender,;`message,;`fname (f for file)
*                ^^ fname is the name of just the file, no directory
*
*       (so the delimiter character needs to be something that we're sure no
*        user would ever type in, hence the triple comma)
*
* STRUCTURE:
*
*   ReadWriteMessage
*       public ReadWriteMessage(String dbname, String filename)
*       public ArrayList<Message> read(String username)
*       public void write_text_message(TextMessage m)
*       public void write_url_message(URLMessage u)
*       public void write_file_message(FileMessage f)
*       public void update_nm_value(String username)
*
*/

import java.io.FileWriter;
import java.io.File;

import java.util.Scanner;
import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteMessage extends ReadWrite<ArrayList<Message>>
{
    public ReadWriteMessage(String dbname, String filename) throws Exception
    {
        super(dbname, filename);
        //this.out.write("test line three");
        //this.out.flush();
        //while(this.in.hasNext())
        //{
        //    System.out.println(this.in.nextLine());
        //}
    }

    /**
    * given a username returns their entire message history
    */

    public ArrayList<Message> read(String username) throws Exception
    {
        String info;
        String[] full_info;
        ArrayList<Message> msgs = new ArrayList<Message>();
        while(this.in.hasNext())
        {
            info = this.in.nextLine();
            full_info = info.split(",;`");
            //System.out.println(full_info.length);
            //System.out.println(full_info[0]);
            //System.out.println(username + "\n\n");
            if((full_info[0].equals(username)) && (full_info[1].equals("t")))
            {
                msgs.add(new TextMessage(full_info[0], full_info[2],
                                         full_info[3]));
            }
            else if((full_info[0].equals(username)) && (full_info[1].equals("u")))
            {
                msgs.add(new URLMessage(full_info[0], full_info[2],
                                        full_info[3], full_info[4]));
            }
            else if((full_info[0].equals(username)) && (full_info[1].equals("f")))
            {
                msgs.add(new FileMessage(full_info[0], full_info[2],
                                         full_info[3], full_info[4]));
            }
        }
        // get every message where the recipient is 'username'
        return msgs;
    }



    public void write(ArrayList<Message> msgs) throws Exception
    {
        // do stuff here
    }

    /**
    * save a text message in messages.txt
    */

    public void write_text_message(TextMessage m) throws Exception
    {
        String info =m.getRecipient() + DELIMITER +  "t" + DELIMITER +
                     m.getSender() + DELIMITER + m.getMessageContent() + "\n";
        //System.out.println(info);
        this.out.write(info);
        this.out.flush();
        this.update_nm_value(m.getRecipient());
    }

    /**
    * save a url message in messages.txt
    */

    public void write_url_message(URLMessage u) throws Exception
    {
        String info = u.getRecipient() + DELIMITER + "u" + DELIMITER +
                u.getSender() + DELIMITER + u.getMessageContent() + DELIMITER +
                u.getURL() + "\n";
        this.out.write(info);
        this.out.flush();
        this.update_nm_value(u.getRecipient());
    }

    /**
    * save a file message in messages.txt
    * (file messages work as long as the file is in the files folder)
    */

    public void write_file_message(FileMessage f) throws Exception
    {
        String info = f.getRecipient() + DELIMITER + "f" + DELIMITER +
               f.getSender() + DELIMITER + f.getMessageContent() + DELIMITER +
               f.getFname() + "\n";
        this.out.write(info);
        this.out.flush();
        this.update_nm_value(f.getRecipient());
    }

    /**
    * edits the new_messages value of the recipient, adding 1 to it
    */

    public void update_nm_value(String username) throws Exception
    {
        ReadWriteAccount acc = new ReadWriteAccount("data.db");
        acc.write_new_messages_column(username, 1);
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteMessage m = new ReadWriteMessage("data.db","messages.txt");
        TextMessage msg1 = new TextMessage("face","energised","hello face");
        TextMessage msg2 = new TextMessage("gman","energised","wellwellwell");
        //m.write_text_message(msg1);
        //m.write_text_message(msg2);
        URLMessage msg3 = new URLMessage("energised","gman","look","www.google.com");
        //m.write_url_message(msg3);
        FileMessage msg4 = new FileMessage("energised","face","hey listen","music.mp4");
        //m.write_file_message(msg4);
        ArrayList<Message> msgs = m.read("energised");
        //System.out.println(msgs);
        for(Message x : msgs)
        {
            System.out.println(x.getRecipient());
        }
    }
}
