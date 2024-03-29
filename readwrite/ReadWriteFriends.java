/**
* ReadWriteFriends.java
* @author Dan Woolsey
*
* Allows the storage of friendships between accounts in a database to be used
* by Graph to display friends instead of a contact list for each user
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
*   -> List will be of type ArrayList<Account> so each 2 accounts stored in order will be friends
*
* USAGE:
*   javac ReadWriteFriends.java
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteFriends
*       ^^ NB: ; for Windows, : for UNIX based systems
*
* STRUCTURE:
*
*   ReadWriteFriends
*       public ReadWriteFriends(String filename)
*       public Statement create_friends_table(Connection conn, Statement stmt)
*       public ArrayList<Account> read(String query)
*           public ArrayList<Account> get_all_friends() (TO USE WITH GRAPH)
*           public Account get_account_from_id(int acc_id)
*       public void write(ArrayList<Account> to_add)
*           public int check_friends_status(Account a1, Account a2)
*           public int get_acc_id(Account acc)
*/

import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteFriends extends ReadWrite<ArrayList<Account>>
{

    /**
    * Sets up a connection to the db
    * @param filename The db we want to use
    */

    public ReadWriteFriends(String filename) throws Exception
    {
        super(filename);
        //this.conn = connect_to_db(this.conn);
        this.stmt = create_friends_table(this.conn, this.stmt);
    }

    /**
    * Runs an SQL statement to create the Friends table
    * @param conn The connection object to the db
    * @param stmt Null pointing statement to be set up
    * @return the setup statement object
    */

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

    /**
    * unused method
    */

    public ArrayList<Account> read(String query) throws Exception
    {
        return new ArrayList<Account>();
    }

    /**
    * - get a table of all friendships stored in the database
    * - return list of account objects where:
    *       [acc1, acc2, acc1, acc5, acc3, acc6] means
    *           : acc1 and acc2 are FRIENDS
    *           : acc1 and acc5 are FRIENDS
    *           : acc3 and acc6 are FRIENDS
    */

    public ArrayList<Account> get_all_friends() throws Exception
    {
        ArrayList<Account> friends = new ArrayList<Account>();
        String sql = "SELECT a.User1ID as 'u1', a.User2ID as 'u2' " +
                     "FROM Friends as 'a', Friends as 'b' " +
                     "WHERE a.User1ID = b.User2ID " +
                     "AND a.User2ID = b.User1ID;";
        //PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.stmt.executeQuery(sql);
        while(rs.next())
        {
            friends.add(get_account_from_id(rs.getInt("u1")));
            friends.add(get_account_from_id(rs.getInt("u2")));
            //System.out.println(rs.getInt("u1"));
            //System.out.println(rs.getInt("u2"));
            rs.next(); // remove the second record we know will be there so we can just get account id's
        }
        return friends;
    }

    /**
    * Given some account, computes all friend requests they'll have recieved
    * @param acc The account we're checking
    * @return ArrayList of all pending friend requests for some Account
    */

    public ArrayList<Account> get_all_requests(Account acc) throws Exception
    {
        ArrayList<Account> requests = new ArrayList<Account>();
        int id = get_acc_id(acc);
        String sql = "SELECT User1ID, User2ID from Friends " +
                     "WHERE User2ID = ? " +
                     "EXCEPT " +
                     "SELECT a.User1ID as 'u1', a.User2ID as 'u2' " +
                     "FROM Friends as 'a', Friends as 'b' " +
                     "WHERE a.User1ID = b.User2ID " +
                     "AND a.User2ID = b.User1ID " +
                     "AND a.User2ID = ?";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,id);
        p_stmt.setInt(2,id);
        ResultSet rs = p_stmt.executeQuery();
        while(rs.next())
        {
            //System.out.println(rs.getInt("User1ID"));
            requests.add(get_account_from_id(rs.getInt("User1ID")));
        }
        return requests;
    }

    /**
    * Given an AccountID, pull information from the database and generate an
    * account object
    * @param acc_id The AccountID we're using
    * @return The account we want
    */

    public Account get_account_from_id(int acc_id) throws Exception
    {
        Account acc = null;
        String sql = "SELECT * from Account " +
                     "WHERE AccountID = ?;";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,acc_id);
        ResultSet rs = p_stmt.executeQuery();
        while(rs.next())
        {
            acc = new Account(rs.getString("username"), rs.getString("first_name"),
            rs.getString("surname"), rs.getString("mob_num"), rs.getString("dob"),
            rs.getString("city"), rs.getInt("new_messages"), rs.getString("prev_session"),
            rs.getString("profile_img"));
        }
        return acc;
    }

    /**
    * to_add will contain: [Account1, Account2]
    *   where both are Account objects
    *
    * inserts a record for two accounts into the database
    * checks if it's a request or an acceptance
    * notifys/updates accordingally
    */

    public void write(ArrayList<Account> to_add) throws Exception
    {
        ReadWriteAccount acc = new ReadWriteAccount("data.db"); // make data.db a constant
        int user1 = acc.read_int_column(to_add.get(0).getUsername(), "AccountID");
        int user2 = acc.read_int_column(to_add.get(1).getUsername(), "AccountID");
        String sql = "INSERT INTO Friends(User1ID, User2ID) VALUES(?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setString(1,Integer.toString(user1));
        p_stmt.setString(2,Integer.toString(user2));
        p_stmt.executeUpdate(); // edit out when not adding data
        int status = check_friends_status(to_add.get(0), to_add.get(1));
        if(status == 1)
        {
            //  notify user of a pending friendship request
            System.out.println("pending friend request");
        }
        else if(status == 2)
        {
            // add relationship to the graph
            // cant do until we get a reference to Graph.java
            // from Main.java
            System.out.println("friend request accepted");
        }
        else
        {
            // some error has occurred
            System.out.println("error");
        }

    }

    /**
    * Determines if 2 accounts have a pending or accepted friendship relation
    *
    * if 1 is returned then friendship is PENDING
    * if 2 is returned then friendship is ACCEPTED
    *
    */

    public int check_friends_status(Account a1, Account a2) throws Exception
    {
        String sql = "SELECT count(FriendshipID) FROM Friends " +
                     "WHERE User1ID = ? OR User2ID = ?";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,get_acc_id(a1));
        p_stmt.setInt(2,get_acc_id(a2));
        ResultSet rs = p_stmt.executeQuery();
        int ans = 0;
        while(rs.next())
        {
            ans = rs.getInt("count(FriendshipID)");
        }
        return ans;
    }

    /**
    * Get information about an account given the desired column and the
    * relevent Account object
    */

    public int get_acc_id(Account acc) throws Exception
    {
        String sql = "SELECT AccountID FROM Account WHERE username = ?";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        //System.out.println("uname: " + acc.getUsername());
        p_stmt.setString(1,acc.getUsername());
        ResultSet rs = p_stmt.executeQuery();
        int ans = 0;
        while(rs.next())
        {
            ans = rs.getInt("AccountID");
        }
        return ans;
    }

    /**
    * Testing for ReadWriteFriends functionality
    */

    public static void main(String[] args) throws Exception
    {
        ReadWriteFriends r = new ReadWriteFriends("data.db");
        // create 2 account lists to build the friendships
        ArrayList<Account> acc = new ArrayList<Account>();
        Account a1 = new Account("energised", "dan", "woolsey", "01234567891",
                            "01/01/1990", "swansea", 0, null, "energised.png");
        Account a2 = new Account("gman", "gary", "tam", "19876543210",
                                  "12/02/1900", "swansea", 0, null, "gman.png");
        Account a3 = new Account("face", "ben", "ten", "07543211964",
                                 "11/12/1700", "aesnaws", 0, null, "face.png");
        acc.add(a1); // energised sends request to gman
        acc.add(a2);
        ArrayList<Account> acc2 = new ArrayList<Account>();
        acc2.add(a2); // gman accepts energiseds request
        acc2.add(a1);
        ArrayList<Account> acc3 = new ArrayList<Account>();
        acc3.add(a3); // face adds energised
        acc3.add(a1);
        ArrayList<Account> acc4 = new ArrayList<Account>();
        acc4.add(a1); // energised accepts
        acc4.add(a3);

        //r.write(acc); // add a record to the db
        //r.write(acc2); // add another
        //r.write(acc3);
        //r.write(acc4);

        ArrayList<Account> f = r.get_all_friends(); // return all users who are friends

        int count = 0;
        Account fa1 = null;
        Account fa2 = null;

        // loop example for BST when we need to retrieve friendships between accounts
        while(count < f.size())
        {
            fa1 = f.get(count);
            fa2 = f.get(count+1);
            System.out.println(fa1.getUsername());
            System.out.println(fa2.getUsername());
            count += 2;
        }

        //System.out.println(r.get_acc_id(acc.get(0)));
        //System.out.println(r.get_acc_col("AccountID", acc.get(1)));

        ArrayList<Account> a = r.get_all_requests(a1);

        for(Account x : a)
        {
            System.out.println(x.getUsername());
        }
    }
}
