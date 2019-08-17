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
* USAGE:
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteMessage
*     ^^ NB: ; for Windows, : for UNIX based systems
*
* STRUCTURE:
*
*   ReadWriteMessage
*       public ReadWriteMessage(String dbname, String filename)
*       public ArrayList<Message> read(String username)
*       public void write_text_message(TextMessage m)
*       public void write_url_message(URLMessage u)
*       public void write_file_message(FileMessage f)
*       public void inc_nm_value(String username)
*       public void dec_nm_value(String username)
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

    /**
    * Sets a connection up to the db and messages.txt
    * @param dbname the db
    * @param filename the message file
    */

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
    * given a username, it returns their entire message history
    * @param username The user
    * @return an ArrayList of Messages
    */

    public ArrayList<Message> read(String username) throws Exception
    {
        String info;
        String[] full_info;
        ArrayList<Message> msgs = new ArrayList<Message>();
        ArrayList<TextMessage> text = new ArrayList<TextMessage>();
        ArrayList<URLMessage> url = new ArrayList<URLMessage>();
        ArrayList<FileMessage> file = new ArrayList<FileMessage>();
        while(this.in.hasNext())
        {
            info = this.in.nextLine();
            full_info = info.split(",;`");
            //System.out.println(full_info.length);
            //System.out.println(full_info[0]);
            //System.out.println(username + "\n\n");
            if((full_info[0].equals(username)) && (full_info[1].equals("t")))
            {
                text.add(new TextMessage(full_info[0], full_info[2],
                                         full_info[3]));
            }
            else if((full_info[0].equals(username)) && (full_info[1].equals("u")))
            {
                url.add(new URLMessage(full_info[0], full_info[2],
                                        full_info[3], full_info[4]));
            }
            else if((full_info[0].equals(username)) && (full_info[1].equals("f")))
            {
                file.add(new FileMessage(full_info[0], full_info[2],
                                         full_info[3], full_info[4]));
            }
        }
        return msgs;
    }

    /**
    * Gets all TextMessages recieved by a user
    */

    public ArrayList<TextMessage> read_text_messages(String username)
    {
        String info;
        String[] full_info;
        ArrayList<TextMessage> text = new ArrayList<TextMessage>();
        while(this.in.hasNext())
        {
            info = this.in.nextLine();
            full_info = info.split(",;`");
            //System.out.println(full_info.length);
            //System.out.println(full_info[0]);
            //System.out.println(username + "\n\n");
            if((full_info[0].equals(username)) && (full_info[1].equals("t")))
            {
                text.add(new TextMessage(full_info[0], full_info[2],
                                         full_info[3]));
            }
        }
        return text;
    }

    /**
    * Gets all URLMessages recieved by a user
    */

    public ArrayList<URLMessage> read_url_messages(String username)
    {
        String info;
        String[] full_info;
        ArrayList<URLMessage> url = new ArrayList<URLMessage>();
        while(this.in.hasNext())
        {
            info = this.in.nextLine();
            full_info = info.split(",;`");
            //System.out.println(full_info.length);
            //System.out.println(full_info[0]);
            //System.out.println(username + "\n\n");
            if((full_info[0].equals(username)) && (full_info[1].equals("u")))
            {
                url.add(new URLMessage(full_info[0], full_info[2],
                                         full_info[3], full_info[4]));
            }
        }
        return url;
    }

    /**
    * Gets all FileMessages recieved by a user
    */

    public ArrayList<FileMessage> read_file_messages(String username)
    {
        String info;
        String[] full_info;
        ArrayList<FileMessage> file = new ArrayList<FileMessage>();
        while(this.in.hasNext())
        {
            info = this.in.nextLine();
            full_info = info.split(",;`");
            //System.out.println(full_info.length);
            //System.out.println(full_info[0]);
            //System.out.println(username + "\n\n");
            if((full_info[0].equals(username)) && (full_info[1].equals("f")))
            {
                file.add(new FileMessage(full_info[0], full_info[2],
                                         full_info[3], full_info[4]));
            }
        }
        return file;
    }

    public void write(ArrayList<Message> msgs) throws Exception
    {
        // unused
    }

    /**
    * save a text message in messages.txt
    */

    public void write_text_message(TextMessage m) throws Exception
    {
        String info = m.getRecipient() + DELIMITER +  "t" + DELIMITER +
                      m.getSender() + DELIMITER + m.getMessageContent() + "\n";
        //System.out.println(info);
        this.out.write(info);
        this.out.flush();
        this.inc_nm_value(m.getRecipient());
    }

    /**
    * Stores a URLMessage object as a string in 'messages.txt'
    */

    public void write_url_message(URLMessage u) throws Exception
    {
        String info = u.getRecipient() + DELIMITER + "u" + DELIMITER +
                u.getSender() + DELIMITER + u.getMessageContent() + DELIMITER +
                u.getURL() + "\n";
        this.out.write(info);
        this.out.flush();
        this.inc_nm_value(u.getRecipient());
    }

    /**
    * Stores a FileMessage object as a string in 'messages.txt'
    * (file messages work as long as the file is in the files folder)
    * @param f The FileMessage object
    */

    public void write_file_message(FileMessage f) throws Exception
    {
        String info = f.getRecipient() + DELIMITER + "f" + DELIMITER +
               f.getSender() + DELIMITER + f.getMessageContent() + DELIMITER +
               f.getFname() + "\n";
        this.out.write(info);
        this.out.flush();
        this.inc_nm_value(f.getRecipient());
    }


    /**
    * Sends a TextMessage object to a list of recipients
    * @param recipients ArrayList of usernames
    * @param msg The TextMessage object
    */

    public void write_multi_text_msg(ArrayList<String> recipients, TextMessage msg) throws Exception
    {
        for(String recipient : recipients)
        {
            msg.setRecipient(recipient);
            this.write_text_message(msg);
        }
    }

    /**
    * Sends a URLMessage object to a list of recipients
    * @param recipients ArrayList of usernames
    * @param msg The URLMessage object
    */

    public void write_multi_url_message(ArrayList<String> recipients, URLMessage msg) throws Exception
    {
        for(String recipient : recipients)
        {
            msg.setRecipient(recipient);
            this.write_url_message(msg);
        }
    }

    /**
    * Sends a FileMessage object to a list of recipients
    * @param recipients ArrayList of usernames
    * @param msg The FileMessage object
    */

    public void write_multi_file_message(ArrayList<String> recipients, FileMessage msg) throws Exception
    {
        for(String recipient : recipients)
        {
            msg.setRecipient(recipient);
            this.write_file_message(msg);
        }
    }

    /**
    * Increments the new_messages value of a user by 1
    * @param username The account we're editing
    */

    public void inc_nm_value(String username) throws Exception
    {
        ReadWriteAccount acc = new ReadWriteAccount("data.db");
        acc.write_new_messages_column(username, 1);
    }

    /**
    * Decrements the new_messages value of a user by 1
    * @param username The account we're editing
    */

    public void dec_nm_value(String username) throws Exception
    {
        ReadWriteAccount acc = new ReadWriteAccount("data.db");
        acc.write_new_messages_column(username, -1);
    }

    /**
    * Implemented for testing purposes
    */

    public static void main(String[] args) throws Exception
    {
        ReadWriteMessage m = new ReadWriteMessage("data.db","messages.txt");
        TextMessage msg1 = new TextMessage("energised","face","hello world!");
        TextMessage msg2 = new TextMessage("energised","gman","wellwellwell");
        m.write_text_message(msg1);
        m.write_text_message(msg2);
        URLMessage msg3 = new URLMessage("energised","gman","look","www.google.com");
        //m.write_url_message(msg3);
        FileMessage msg4 = new FileMessage("energised","face","hey listen","music.mp4");
        //m.write_file_message(msg4);
        ArrayList<Message> msgs = m.read("energised");
        //System.out.println(msgs);
        //for(Message x : msgs)
        //{
        //    System.out.println(x.getRecipient());
        //}
        // testing multi user message functionality
        ArrayList<String> rec = new ArrayList<String>();
        rec.add("face");
        rec.add("gman");
        //rec.add("energised");
        //m.write_multi_text_msg(rec,msg1);
        //for(String x : rec)
        //{
        //    m.dec_nm_value(x);
        //    m.dec_nm_value(x);
        //}
    }
}
