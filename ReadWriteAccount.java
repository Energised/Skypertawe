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
*/

// import java.io.PrintWriter;
// import java.io.File;

import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ReadWriteAccount extends ReadWrite
{
    public ReadWriteAccount(String filename)
    {
        super(filename);
        System.out.println(this.filename);
    }

    public static void main(String[] args)
    {
        ReadWriteAccount r = new ReadWriteAccount("data.db");
    }

    public void read() {};
    public void write() {};
}
