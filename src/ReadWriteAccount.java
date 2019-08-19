/**
 * ReadWriteAccount.java
 * @author Dan Woolsey
 *
 * FUNCTION LIST:
 *      (y) ReadWriteAccount(String dbname)
 *      (y) CreateAccountTable(Conenction conn, Statement stmt)
 *
 *      WRITING FUNCTIONS
 *      (y) WriteNewAccount(Account ac)
 *      (y) WriteUpdateAccount(String username, String col, String value)
 *      (y) WriteNewMessages(String username)
 *
 *      READING FUNCTIONS
 *      (y) ReadUserAccount(String username)
 *      (y) ReadAllAccounts()
 *      (y) ReadNewMessages(String username)
 *
 * NB: RUN: java -cp ".:sqlite-jdbc-3.15.1.jar" ReadWriteAccount
 *     When dealing with SQLite; all function must be able to throw exceptions
 */

package src;

import src.obj.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ReadWriteAccount extends ReadWrite<Account>
{

    /**
    * Connects to database and builds table Account
    * @param dbname Name of database
    */

    public ReadWriteAccount(String dbname) throws Exception
    {
        super(dbname); // if db doesnt exist, will create new db
        this.stmt = CreateAccountTable(this.conn, this.stmt);
    }

    /**
    * Creates table Account if not already built
    * @param conn Connection object to database
    * @param stmt Statement object associated with RWA
    */

    public Statement CreateAccountTable(Connection conn, Statement stmt) throws Exception
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
                         "prev_session VARCHAR(20)," +
                         "profile_img VARCHAR(40)," +
                         "UNIQUE (AccountID, username));";
        stmt.executeUpdate(acc_sql);
        return stmt;
    }

    // WRITE FUNCTIONS

    /**
    * Insert new account to the database
    * @param ac Account object to be inserted
    */

    public void WriteNewAccount(Account ac) throws Exception
    {
        String add_record = "INSERT INTO Account(username, first_name, " +
                             "surname, mob_num, dob, city, profile_img) " +
                             "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(add_record);
        p_stmt.setString(1, ac.getUsername());
        p_stmt.setString(2, ac.getFirstName());
        p_stmt.setString(3, ac.getSurname());
        p_stmt.setString(4, ac.getMobnumber());
        p_stmt.setString(5, ac.getBirthDate());
        p_stmt.setString(6, ac.getCity());
        p_stmt.setString(7, ac.getImgPath());
        p_stmt.executeUpdate();
    }

    /**
    * Update a column of a users database record
    * @param username User being updated
    * @param col Column to update
    * @param value New value to insert
    */

    public void WriteUpdateAccount(String username, String col, String value) throws Exception
    {
        String sql;
        sql = "UPDATE Account " + "SET '" + col + "' = '" + value
              + "' " + "WHERE username = '" + username + "';";
        this.stmt.executeUpdate(sql);
    }

    /**
    * Increment the users new_messages column by 1
    * @param username Given user to update
    */

    public void WriteNewMessages(String username) throws Exception
    {
        int currentValue = this.ReadNewMessages(username);
        String sql = "UPDATE Account " +
                     "SET new_messages = ? " +
                     "WHERE username = ?;";
        PreparedStatement p_stmt = this.conn.prepareStatement(sql);
        p_stmt.setInt(1,currentValue+1);
        p_stmt.setString(2,username);
        p_stmt.executeUpdate();
    }

    // READ FUNCTIONS

    public Account ReadUserAccount(String username) throws Exception
    {
        String sql = "SELECT * FROM Account WHERE username = ?;";
        PreparedStatement p_sql = this.conn.prepareStatement(sql);
        p_sql.setString(1,username); // query by username
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

    public ArrayList<Account> ReadAllAccounts() throws Exception
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

    public int ReadNewMessages(String username) throws Exception
    {
        String sql = "SELECT new_messages FROM Account WHERE username='" + username + "';";
        ResultSet rs = this.stmt.executeQuery(sql);
        int newMsg = 0;
        while(rs.next())
        {
            newMsg = rs.getInt(1);
        }
        return newMsg;
    }

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount rwa = new ReadWriteAccount("data.db");

        //Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
        //Account ac2 = new Account("gman", "Gary", "Waho", "07649752134", "7/12", "Bradford", 0, null, "profile-img.jpg");
        //Account ac3 = new Account("bobby", "Bob", "Cobb", "07432257152", "9/11", "Ramsgate", 0, null, "profile-img.jpg");
        Account ac4 = new Account("zapdos", "Ash", "Smash", "07477957152", "12/05", "Basildon", 0, null, "profile-img.jpg");
        Account ac5 = new Account("coleslaw", "Jack", "Cross", "07432217152", "22/11", "Hackney", 0, null, "profile-img.jpg");
        Account ac6 = new Account("smokeweed4lyf", "Adam", "Fladam", "07342257192", "01/01", "York", 0, null, "profile-img.jpg");
        //rwa.WriteNewAccount(ac4);
        //rwa.WriteNewAccount(ac5);
        //rwa.WriteNewAccount(ac6);
        //rwa.WriteUpdateAccount("energised", "city", "Southend");
        //rwa.WriteNewMessages("energised");
        //System.out.println(rwa.ReadNewMessages("energised"));
        Account result = rwa.ReadUserAccount("zapdos");
        System.out.println(result.getFirstName() + " " + result.getSurname());
        ArrayList<Account> acList = rwa.ReadAllAccounts();
        for(Account x : acList)
        {
            System.out.println(x.getUsername() + " - " + x.getFirstName() + " " + x.getSurname());
        }
    }
}
