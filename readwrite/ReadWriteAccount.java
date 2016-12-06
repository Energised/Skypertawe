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
* For running ReadWriteAccount:
*   javac ReadWriteAccount.java
*   java -cp ".;sqlite-jdbc-3.15.1.jar" ReadWriteAccount
*     ^^ NB: ; for Windows, : for UNIX based systems
*
* STILL TO DO:
*   -> Rewrite write() method to take an Account object instead of an ArrayList
*   -> Add update() method for editing info on a profile
*/

// import java.io.PrintWriter;
// import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteAccount extends ReadWrite<ArrayList<String>>
{

    public ReadWriteAccount(String filename) throws Exception
    {
        super(filename);
        this.conn = connect_to_db(this.conn);
        this.stmt = create_account_table(this.conn, this.stmt);
        //this.acc_info = new ArrayList<String>();
    }

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        return conn;
    }

    public Statement create_account_table(Connection conn, Statement stmt) throws Exception
    {
        stmt = conn.createStatement();
        String acc_sql = "CREATE TABLE IF NOT EXISTS Account(" +
                         "userID INTEGER PRIMARY KEY," +
                         "username VARCHAR(40) NOT NULL," +
                         "first_name VARCHAR(25) NOT NULL," +
                         "surname VARCHAR(25) NOT NULL," +
                         "mob_num VARCHAR(11)," +
                         "dob VARCHAR(10)," +
                         "city VARCHAR(30)," +
                         "new_messages INTEGER," +
                         "prev_session VARCHAR(15)," +
                         "profile_img VARCHAR(40)," +
                         "UNIQUE (userID, username));";
        stmt.executeUpdate(acc_sql);
        return stmt;
    }

    /**
    * Runs a query by username on the database of accounts
    * @param query ArrayList of one argument, the searched for username
    *
    * nb: read will only take an ArrayList so check other classes ask for info using this
    */

    public void read(String query) throws Exception
    {
        String sql = "SELECT * FROM Account WHERE username = ?;";
        PreparedStatement p_sql = this.conn.prepareStatement(sql);
        p_sql.setString(1,query); // query by username
        ResultSet rs = p_sql.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getInt("userID"));
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("first_name"));
            System.out.println(rs.getString("surname"));
            System.out.println();
        }
    }

    /**
    * acc_info will contain: [username, first_name, surname, mob_num,
                              dob, city, prof_img]
    * in that EXACT order.
    */

    public void write(ArrayList<String> acc_info) throws Exception
    {
        String add_record = "INSERT INTO Account(username, first_name, " +
                             "surname, mob_num, dob, city, profile_img) " +
                             "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement p_stmt = this.conn.prepareStatement(add_record);
        int counter = 1;
        for(String info : acc_info)
        {
            p_stmt.setString(counter, info);
            counter += 1;
        }
        p_stmt.executeUpdate();
    }

    /**
    * Only used for testing purposes
    */

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount r = new ReadWriteAccount("data.db");
        ArrayList<String> acc = new ArrayList<String>(); // can be replaced with an account object once that class is done
        acc.add("energised");
        acc.add("dan");
        acc.add("woolsey");
        acc.add("07772638833");
        acc.add("17/04/1997");
        acc.add("swansea");
        acc.add("img/dan.png");
        //r.add_new_data(acc);
        //r.write(acc);
        r.read("energised");
    }
}
