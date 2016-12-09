/**
* ReadWrite.java
* @author Dan Woolsey
*
* Base class for all ReadWrite functionality within Skypertawe
*
* TODO: close scanner at some point
*/

import java.io.PrintWriter;
import java.io.File;

import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

abstract class ReadWrite<E>
{
    Scanner in;
    PrintWriter out;
    String dbname;
    String filename;
    Connection conn = null;
    Statement stmt = null;

    /**
    * when only the database is needed by a ReadWrite subclass
    */

    public ReadWrite(String dbname) throws Exception
    {
        this.dbname = "data/" + dbname;
        this.conn = connect_to_db(this.conn);
    }

    /**
    * when the database and a text file is needed by a ReadWrite subclass
    */

    public ReadWrite(String dbname, String filename) throws Exception
    {
        this.dbname = "data/" + dbname;
        this.filename = "data/" + filename;
        this.conn = connect_to_db(this.conn);
        this.in = connect_to_file(this.in);
    }

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String conn_info = "jdbc:sqlite:" + this.dbname;
        conn = DriverManager.getConnection(conn_info);
        return conn;
    }

    public Scanner connect_to_file(Scanner in)
    {
        File file = new File(this.filename);
        try
        {
            in = new Scanner(file);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return in;
    }

    public abstract E read(String query) throws Exception;
    public abstract void write(E data) throws Exception;

}
