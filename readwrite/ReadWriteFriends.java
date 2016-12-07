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
* Writing Friendships:
*   -> some user adds another user (get AccountID's from db)
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
*
* TODO:
*   -> Make "data.db" a constant
*   -> Finish read/write functionality
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

public class ReadWriteFriends extends ReadWrite<ArrayList<Account>>
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

    public ArrayList<Account> read(String a) throws Exception
    {
        return new ArrayList<Account>();
    }

    /**
    * to_add will contain: [Account1, Account2]
    *   where both are Account objects
    *
    * currently just takes a list of 2 accounts and makes them friends
    */

    public void write(ArrayList<Account> to_add) throws Exception
    {
        ReadWriteAccount acc = new ReadWriteAccount("data.db"); // make data.db a constant
        int user1 = acc.read_int_column(to_add.get(0).getUsername(), "AccountID");
        int user2 = acc.read_int_column(to_add.get(1).getUsername(), "AccountID");
        String sql = "INSERT INTO Friends(User1ID, User2ID) VALUES(?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setString(1,user1); // change user1 and user2 to be string types
        p_stmt.setString(2,user2);
        p_stmt.executeUpdate();
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteFriends r = new ReadWriteFriends("data.db");
        ArrayList<Account> acc = new ArrayList<Account>();
        acc.add(new Account("energised", "dan", "woolsey", "01234567891",
                                  "01/01/1990", "swansea", 0, null, "energised.png"));
        acc.add(new Account("gman", "gary", "tam", "19876543210",
                                  "12/02/1900", "swansea", 0, null, "gman.png"));
        r.write(acc);
    }
}
