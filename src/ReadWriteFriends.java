/**
 * ReadWriteFriends.java
 * @author Dan Woolsey
 *
 * ReadWriteFriends implementation refactored
 *
 * (y) ReadWriteFriends()
 * (y) createFriendsTable()
 *      READ FUNCTIONS
 *      (y) getIDFromAccount()
 *      (y) getAccountFromID()
 *      (y) getAllFriendships()
 *      (y) getUserRequests()
 *      WRITE FUNCTION
 *      (y) setFriendshipRecord()
 *
 */

package src;

import java.util.Arrays;
import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteFriends extends ReadWrite
{
    public ReadWriteFriends(String filename) throws Exception
    {
        super(filename);
        this.createFriendsTable(this.conn, this.stmt);
    }

    /**
    * Runs an SQL statement to create the Friends table
    * @param conn The connection object to the db
    * @param stmt Null pointing statement to be set up
    * @return the setup statement object
    */

    public Statement createFriendsTable(Connection conn, Statement stmt) throws Exception
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

    // WRITE FUNCTION

    /**
     * Insert record using AccountIDs - (ID1 + ID2 && ID2 + ID1) == FRIENDS
     * @param toAdd List of 2 accounts to be added
     */

    public void setFriendshipRecord(ArrayList<Account> toAdd) throws Exception
    {
        int id1 = this.getIDFromAccount(toAdd.get(0));
        int id2 = this.getIDFromAccount(toAdd.get(1));
        String sql = "INSERT INTO Friends(User1ID, User2ID) VALUES(?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setString(1,Integer.toString(id1));
        p_stmt.setString(2,Integer.toString(id2));
        p_stmt.executeUpdate();
    }

    // READ FUNCTIONS

    public int getIDFromAccount(Account acc) throws Exception
    {
        String sql = "SELECT AccountID FROM Account WHERE username = ?";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setString(1,acc.getUsername());
        ResultSet rs = p_stmt.executeQuery();
        int ans = 0;
        while(rs.next())
        {
            ans = rs.getInt("AccountID");
        }
        return ans;
    }

    public Account getAccountFromID(int id) throws Exception
    {
        Account acc = null;
        String sql = "SELECT * from Account " +
                     "WHERE AccountID = ?;";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,id);
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
     * Stores Account pairs indicating friendships
     * e.g. {acc1, acc2, acc3, acc4, acc5, acc6} - acc1 + acc2 = FRIENDS
     *                                             acc3 + acc4 = FRIENDS etc...
     */

    public ArrayList<Account> getAllFriendships() throws Exception
    {
        ArrayList<Account> friends = new ArrayList<Account>();
        String sql = "SELECT a.User1ID as 'u1', a.User2ID as 'u2' " +
                     "FROM Friends as 'a', Friends as 'b' " +
                     "WHERE a.User1ID = b.User2ID " +
                     "AND a.User2ID = b.User1ID;";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        ResultSet rs = p_stmt.executeQuery(); // stmt.executeQuery(sql); // idk why this didnt work :p
        while(rs.next())
        {
            friends.add(getAccountFromID(rs.getInt("u1")));
            friends.add(getAccountFromID(rs.getInt("u2")));
            // remove the second record we know will be there to give 1 record for each
            rs.next();
        }
        return friends;
    }

    public ArrayList<Account> getUserRequests(Account acc) throws Exception
    {
        ArrayList<Account> requests = new ArrayList<Account>();
        int id = getIDFromAccount(acc);
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
            requests.add(getAccountFromID(rs.getInt("User1ID")));
        }
        return requests;
    }

    public static void main(String[] args) throws Exception
    {
        Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
        Account ac2 = new Account("gman", "Gary", "Waho", "07649752134", "7/12", "Bradford", 0, null, "profile-img.jpg");
        Account ac3 = new Account("bobby", "Bob", "Cobb", "07432257152", "9/11", "Ramsgate", 0, null, "profile-img.jpg");
        Account ac4 = new Account("zapdos", "Ash", "Smash", "07477957152", "12/05", "Basildon", 0, null, "profile-img.jpg");
        Account ac5 = new Account("coleslaw", "Jack", "Cross", "07432217152", "22/11", "Hackney", 0, null, "profile-img.jpg");
        Account ac6 = new Account("smokeweed4lyf", "Adam", "Fladam", "07342257192", "01/01", "York", 0, null, "profile-img.jpg");

        ArrayList<Account> r1 = new ArrayList<Account>()
        {{
            add(ac1);
            add(ac2);
        }};
        ArrayList<Account> r2 = new ArrayList<Account>()
        {{
            add(ac2);
            add(ac1);
        }};
        ArrayList<Account> r3 = new ArrayList<Account>()
        {{
            add(ac1);
            add(ac4);
        }};
        ArrayList<Account> r4 = new ArrayList<Account>()
        {{
            add(ac2);
            add(ac5);
        }};

        ArrayList<Account> r5 = new ArrayList<Account>()
        {{
            add(ac5);
            add(ac2);
        }};

        ArrayList<Account> r6 = new ArrayList<Account>()
        {{
            add(ac3);
            add(ac4);
        }};

        ArrayList<Account> r7 = new ArrayList<Account>()
        {{
            add(ac4);
            add(ac3);
        }};

        ArrayList<Account> r8 = new ArrayList<Account>()
        {{
            add(ac3);
            add(ac6);
        }};

        ReadWriteFriends rwf = new ReadWriteFriends("data.db");

        //rwf.setFriendshipRecord(r1);
        //rwf.setFriendshipRecord(r2);
        //rwf.setFriendshipRecord(r3);
        //rwf.setFriendshipRecord(r4);
        //rwf.setFriendshipRecord(r5);
        //rwf.setFriendshipRecord(r6);
        //rwf.setFriendshipRecord(r7);
        //rwf.setFriendshipRecord(r8);

        ArrayList<Account> f = rwf.getAllFriendships();
        for(int x = 0; x<f.size(); x+=2)
        {
            System.out.println("FRIENDS = " + f.get(x).getUsername() + " " +
                                f.get(x+1).getUsername());
        }

        ArrayList<Account> r = rwf.getUserRequests(ac6);
        for(Account x : r)
        {
            System.out.println(ac6.getUsername() + " has a request from " + x.getUsername());
        }
    }
}
