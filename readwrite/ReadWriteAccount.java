/**
* ReadWriteAccount.java
* @author Dan Woolsey
*
* Allows the storing of any created account from CreateAccountGUI and provides
* accessability to information needed by the BST and Graph
*
* Account information will be stored in an SQLite database
*
* When an account is created this class will:
*   Collate the information into a list
*   if no db exists:
*       create table for accounts (sql)
*   insert values into table (sql)
*
* When reading data from a db:
*
*   read_account_info(String username) -> returns a list of info from an sql query on a username
*
* To add a new Account to the db:
*     ReadWriteAccount r = new ReadWriteAccount("data.db");
*     r.write(new_account_object);
*
* USAGE:
*   javac ReadWriteAccount.java
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteAccount
*     ^^ NB: ; for Windows, : for UNIX based systems
*
* STRUCTURE:
*
*   ReadWriteAccount
*       public ReadWriteAccount(String filename)
*       public Statement create_account_table(Connection conn, Statement stmt)
*       public Account read(String query)
*           public ArrayList<Account> read_all_accounts()
*           public int read_int_column(String query, String col)
*           public String read_string_column(String query, String col)
*       public void write(Account acc_info)
*           public void write_new_messages_column(String username, int val)
*           public void write_string_column(String username, String col, String new_val)
*
* STILL TO DO:
*   -> Add read_all_accounts() method
*/

// import java.io.FileWriter;
// import java.io.File;

// import java.util.Scanner;

import java.util.ArrayList;

import java.awt.image.BufferedImage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteAccount extends ReadWrite<Account>
{

    public ReadWriteAccount(String filename) throws Exception
    {
        super(filename);
        //this.conn = connect_to_db(this.conn);
        this.stmt = create_account_table(this.conn, this.stmt);
    }

    public Statement create_account_table(Connection conn, Statement stmt) throws Exception
    {
        stmt = conn.createStatement();
        String acc_sql = "CREATE TABLE IF NOT EXISTS Account(" +
                         "AccountID INTEGER PRIMARY KEY," +
                         "username VARCHAR(40) NOT NULL," +
                         "first_name VARCHAR(25) NOT NULL," +
                         "surname VARCHAR(25) NOT NULL," +
                         "mob_num VARCHAR(11)," +
                         "dob VARCHAR(10)," +
                         "city VARCHAR(30)," +
                         "new_messages INTEGER," +
                         "prev_session VARCHAR(15)," +
                         "profile_img VARCHAR(40)," +
                         "UNIQUE (AccountID, username));";
        stmt.executeUpdate(acc_sql);
        return stmt;
    }

    /**
    * Runs a query by username on the database of accounts
    * @param query ArrayList of one argument, the searched for username
    * @return Account object of the username searched for
    */

    public Account read(String query) throws Exception
    {
        String sql = "SELECT * FROM Account WHERE username = ?;";
        PreparedStatement p_sql = this.conn.prepareStatement(sql);
        p_sql.setString(1,query); // query by username
        ResultSet rs = p_sql.executeQuery();
        Account acc = null;
        while(rs.next())
        {
            acc = new Account(rs.getString("username"), rs.getString("first_name"),
            rs.getString("surname"), rs.getString("mob_num"), rs.getString("dob"),
            rs.getString("city"), rs.getInt("new_messages"), rs.getString("prev_session"),
            rs.getString("profile_img"));
        }
        return acc;
    }

    public ArrayList<Account> read_all_accounts() throws Exception
    {
        ArrayList<Account> acc = new ArrayList<Account>();
        String sql = "SELECT * FROM Account";
        ResultSet rs = this.stmt.executeQuery(sql);
        while(rs.next())
        {
            acc.add(new Account(rs.getString("username"), rs.getString("first_name"),
            rs.getString("surname"), rs.getString("mob_num"), rs.getString("dob"),
            rs.getString("city"), rs.getInt("new_messages"), rs.getString("prev_session"),
            rs.getString("profile_img")));
        }
        return acc;
    }

    /**
    * Given a username, will return the specified col for that user
    * but only if the column type is an int
    *
    * You can get the following columns:
    *   AccountID & new_messages
    */

    public int read_int_column(String query, String col) throws Exception
    {
        String sql = "SELECT * FROM Account WHERE username=?;";
        PreparedStatement p_sql = this.conn.prepareStatement(sql);
        p_sql.setString(1,query);
        ResultSet rs = p_sql.executeQuery();
        int ans = 0;
        while(rs.next())
        {
            ans = rs.getInt(col);
        }
        return ans;
    }

    /**
    * Given a username, return the specified column for that user but only
    * if the type of that column is a string (VARCHAR in SQL)
    *
    * You can get the following columns:
    *   username, first_name, surname, mob_num, dob, city,
    *   prev_session, profile_img
    */

    public String read_string_column(String query, String col) throws Exception
    {
        String sql = "SELECT * FROM Account WHERE username=?;";
        PreparedStatement p_sql = this.conn.prepareStatement(sql);
        p_sql.setString(1,query);
        ResultSet rs = p_sql.executeQuery();
        String ans = null;
        while(rs.next())
        {
            ans = rs.getString(col);
        }
        return ans;
    }

    /**
    * acc_info will contain: [username, first_name, surname, mob_num,
                              dob, city, prof_img]
    * in that EXACT order.
    */

    public void write(Account acc_info) throws Exception
    {
        String add_record = "INSERT INTO Account(username, first_name, " +
                             "surname, mob_num, dob, city, profile_img) " +
                             "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(add_record);
        p_stmt.setString(1, acc_info.getUsername());
        p_stmt.setString(2, acc_info.getFirstName());
        p_stmt.setString(3, acc_info.getSurname());
        p_stmt.setString(4, acc_info.getMobnumber());
        p_stmt.setString(5, acc_info.getBirthDate());
        p_stmt.setString(6, acc_info.getCity());
        p_stmt.setString(7, acc_info.getImgPath());
        p_stmt.executeUpdate();
    }

    /**
    * increment the new_messages column by val for some username
    * (could have made this a general int column updater but the only other
    *  int column is AccountID which will never be changed)
    * (negative values work in order to reset a column)
    */

    public void write_new_messages_column(String username, int val) throws Exception
    {
        int current_value = this.read_int_column(username, "new_messages");
        //System.out.println(current_value);
        String sql = "UPDATE Account " +
                     "SET new_messages = ? " +
                     "WHERE username = ?;";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,current_value+val);
        p_stmt.setString(2,username);
        p_stmt.executeUpdate();
    }

    /**
    * updates a given column with a given value for a given username
    * (PreparedStatements dont allow column names to be passed so had to do it this way)
    */

    public void write_string_column(String username, String col, String new_val) throws Exception
    {
        String sql = "UPDATE Account " +
                     "SET '" + col + "' = '" + new_val + "' " +
                     "WHERE username = '" + username + "';";
        this.stmt.executeUpdate(sql);
    }

    /**
    * Only used for testing purposes
    */

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount r = new ReadWriteAccount("data.db");
        Account acc1 = new Account("energised", "dan", "woolsey", "01234567891",
                                  "01/01/1990", "swansea", 0, null, "energised.png");
        //r.write(acc1); // if record already exists dont rewrite
        Account test = r.read("energised");
        System.out.println(test.getFirstName());
        int acc_id = r.read_int_column("energised","AccountID");
        System.out.println(acc_id);
        String acc_img = r.read_string_column("energised","profile_img");
        System.out.println(acc_img);

        // for use in testing ReadWriteFriends
        Account acc2 = new Account("gman", "gary", "tam", "19876543210",
                                "12/02/1900", "swansea", 0, null, "gman.png");
        //r.write(acc2); // if record already exists dont rewrite it
        Account acc3 = new Account("face", "ben", "ten", "07543211964",
                                 "11/12/1700", "aesnaws", 0, null, "face.png");
        //r.write(acc3); // if record already exists dont rewrite it

        // testing update methods
        //r.write_new_messages_column("energised",-2); // -2 to reset column to 0
        //System.out.println(r.read_int_column("energised","new_messages"));

        //System.out.println(r.read_string_column("energised","surname"));
        //r.write_string_column("energised","surname","person");
        //System.out.println(r.read_string_column("energised","surname"));

        ArrayList<Account> a = r.read_all_accounts();
        for(Account x : a)
        {
            System.out.println(x.getUsername());
        }
    }
}
