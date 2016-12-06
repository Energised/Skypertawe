/**
* ReadWriteFriends.java
* @author Dan Woolsey
*
* Allows the storage of friendships between accounts in a database to be used
* by Graph to display friends instead of a contact list for each user
*
* USAGE:
*   javac ReadWriteFriends.java
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteFriends
*       ^^ NB: ; for Windows, : for UNIX based systems
*/

// import java.io.PrintWriter;
// import java.io.File;

// import java.util.Scanner;

import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteFriends extends ReadWrite<ArrayList<String>>
{
    public ReadWriteFriends(String filename) throws Exception
    {
        super(filename);
        this.conn = connect_to_db(this.conn);
        this.stmt = create_friends_table(this.conn, this.stmt);
    }

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String conn_info = "jdbc:sqlite:" + this.filename;
        conn = DriverManager.getConnection(conn_info);
        return conn;
    }

    public Statement create_friends_table(Connection conn, Statement stmt) throws Exception
    {
        stmt = conn.createStatement();
        String friends_sql = "CREATE TABLE IF NOT EXISTS Friends(" +
                             "FriendshipID INTEGER PRIMARY KEY NOT NULL," +
                             "User1ID INTEGER NOT NULL," +
                             "User2ID INTEGER NOT NULL," +
                             "FOREIGN KEY (User1ID) " +
                             "REFERENCES Account(AccountID));";
        stmt.executeUpdate(friends_sql);
        return stmt;
    }

    public void read(String a) throws Exception
    {
        // do stuff here
    }

    public void write(ArrayList<String> b) throws Exception
    {
        // do stuff here too
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteFriends r = new ReadWriteFriends("data.db");
    }
}
