/**
 * ReadWriteMessage.java
 * @author Dan Woolsey
 *
 * FUNCTION LIST
 *      (y) ReadWriteMessage(String dbname)
 *      (y) CreateMessageTable(Connection conn, Statement stmt)
 *
 *      READ FUNCTIONS
 *          (y) ReadMessages(String username)
 *
 *      WRITE FUNCTIONS
 *          (y) WriteTextMessage(TextMessage msg)
 *          (y) WriteURLMessage(URLMessage msg)
 *          WriteFileMessage()
 *
 * NB: Column 'type' will contain either:
 *          't' -> text
 *          'u' -> URL
 *          'f' -> file
 *
 */

package src;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ReadWriteMessage extends ReadWrite<Message>
{
    public ReadWriteMessage(String dbname) throws Exception
    {
        super(dbname);
        this.stmt = CreateMessageTable(this.conn, this.stmt);
    }

    public Statement CreateMessageTable(Connection conn, Statement stmt) throws Exception
    {
        stmt = conn.createStatement();
        String msg_sql = "CREATE TABLE IF NOT EXISTS Message(" +
                         "MessageID INTEGER PRIMARY KEY," +
                         "type VARCHAR(1)," +
                         "recipient VARCHAR(40)," +
                         "sender VARCHAR(40)," +
                         "dateTime VARCHAR(20)," +
                         "content VARCHAR(400)," +
                         "UNIQUE (MessageID));";
        stmt.executeUpdate(msg_sql);
        return stmt;
    }

    // READ FUNCTIONS

    /**
    * Return all messages sent/recieved by a given user
    * @param username User being searched
    */

    public ArrayList<Message> ReadMessages(String username) throws Exception
    {
        ArrayList<Message> msgs = new ArrayList<Message>();
        String sql = "SELECT type, recipient, sender, dateTime, content FROM " +
                     "Message WHERE recipient = '" + username + "' OR " +
                     "sender = '" + username + "';";
        ResultSet rs = this.stmt.executeQuery(sql);
        while(rs.next())
        {
            if(rs.getString("type").equals("t"))
            {
                msgs.add(new TextMessage(rs.getString("recipient"),
                         rs.getString("sender"), rs.getString("dateTime"),
                         rs.getString("content")));
            }
            else if(rs.getString("type").equals("u"))
            {
                msgs.add(new URLMessage(rs.getString("recipient"),
                         rs.getString("sender"), rs.getString("dateTime"),
                         rs.getString("content")));
            }
        }
        return msgs;
    }

    // WRITE FUNCTIONS

    public void WriteTextMessage(TextMessage msg) throws Exception
    {
        String sql = "INSERT INTO Message(type, recipient, sender," +
                     "dateTime, content) VALUES(?,?,?,?,?)";
        PreparedStatement addRecord = this.conn.prepareStatement(sql);
        addRecord.setString(1, "t");
        addRecord.setString(2, msg.getRecipient());
        addRecord.setString(3, msg.getSender());
        addRecord.setString(4, msg.getDateTime());
        addRecord.setString(5, msg.getMessageContent());
        addRecord.executeUpdate();
    }

    public void WriteURLMessage(URLMessage msg) throws Exception
    {
        String sql = "INSERT INTO Message(type, recipient, sender," +
                     "dateTime, content) VALUES(?,?,?,?,?)";
        PreparedStatement addRecord = this.conn.prepareStatement(sql);
        addRecord.setString(1, "u");
        addRecord.setString(2, msg.getRecipient());
        addRecord.setString(3, msg.getSender());
        addRecord.setString(4, msg.getDateTime());
        addRecord.setString(5,msg.getURL());
        addRecord.executeUpdate();
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteMessage rwm = new ReadWriteMessage("data.db");
        TextMessage msg1 = new TextMessage("energised", "gman", "Wello Horld!");
        //rwm.WriteTextMessage(msg1);
        URLMessage msg2 = new URLMessage("gman", "energised", "www.google.co.uk");
        //rwm.WriteURLMessage(msg2);
        TextMessage msg3 = new TextMessage("bobby", "gman", "hwat you saying bobby");
        //rwm.WriteTextMessage(msg3);
        ArrayList<Message> msgs = rwm.ReadMessages("energised");
        // downcasting will be required due to use of Message base class
        for(Message x : msgs)
        {
            System.out.print("MSG RECIEVED AT: " + x.getDateTime() + " ~ ");
            if(x.getClass() == TextMessage.class)
            {
                System.out.println(((TextMessage) x).getMessageContent());
            }
            if(x.getClass() == URLMessage.class)
            {
                System.out.println(((URLMessage) x).getURL());
            }
        }
    }
}
