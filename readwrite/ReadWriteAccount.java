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
*     r.add_new_data(new_account_info_arraylist);
*     r.write();
*
* USAGE:
*   javac ReadWriteAccount.java
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteAccount
*     ^^ NB: ; for Windows, : for UNIX based systems
*
* STILL TO DO:
*   -> Add update() method for editing info on a profile
*/

// import java.io.PrintWriter;
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
        this.conn = connect_to_db(this.conn);
        this.stmt = create_account_table(this.conn, this.stmt);
    }

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String conn_info = "jdbc:sqlite:" + this.filename;
        conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        return conn;
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
    *
    * nb: read will only take an ArrayList so check other classes ask for info using this
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
    * Only used for testing purposes
    */

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount r = new ReadWriteAccount("data.db");
        Account acc = new Account("energised", "dan", "woolsey", "01234567891",
                                  "01/01/1990", "swansea", 0, null, "energised.png");
        //r.write(acc);
        Account test = r.read("energised");
        System.out.println(test.getFirstName());
    }
}
