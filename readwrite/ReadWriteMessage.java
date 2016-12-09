/**
* ReadWriteMessage.java
* @author Dan Woolsey
*
* Handles storing of sent Message objects into a text file and
* passing them to MessageGUI when a user logs in
*
*
*
*/

import java.io.PrintWriter;
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
        while(this.in.hasNext())
        {
            System.out.println(this.in.nextLine());
        }
    }

    public ArrayList<Message> read(String query) throws Exception
    {
        // do stuff here
        return new ArrayList<Message>();
    }

    public void write(ArrayList<Message> msgs) throws Exception
    {
        // do stuff here
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteMessage m = new ReadWriteMessage("data.db","messages.txt");
    }
}
