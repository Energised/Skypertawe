/**
* ReadWriteFriends.java
* @author Dan Woolsey
*
* Allows the storage of friendships between accounts in a database to be used
* by Graph to display friends instead of a contact list for each user
*
* Needed functions:
*   find_pending_requests() -> return a list of pending friend requests :: ArrayList<(Account1, Account2)>
*   check_friend_status(Account1, Account2) -> returns the number of columns these two accounts share
*
*
* Writing Friendships:
*   -> some user adds another user
*   -> insert tuple into database
*   -> check if its a friend request or acceptance
*   -> if acceptance (count is 2) then update the graph with that
*   -> if request (count is 1) then send a notification to the user (either do this with Graph or with ProfileGUI)
*
* Reading friendships:
*   -> get a table of all the friendships (where count is 2):
*        write sql statement
*   -> return a list of pairs of Account objects that are friends we can compare in Graph.java
*   -> run this everytime a new friendship is made
*
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
