/**
* ReadWrite.java
* @author Dan Woolsey
*
* Base class for all ReadWrite functionality within Skypertawe
*
*/

import java.io.FileWriter;
import java.io.File;

import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

abstract class ReadWrite<E>
{
    Scanner in;
    FileWriter out;
    String dbname;
    String filename;
    Connection conn = null;
    Statement stmt = null;

    final String DELIMITER = ",;`";

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
        this.in = connect_scanner_to_file(this.in);
        this.out = connect_filewriter_to_file(this.out);
    }

    /**
    * make a connection to the database file
    */

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String conn_info = "jdbc:sqlite:" + this.dbname;
        conn = DriverManager.getConnection(conn_info);
        return conn;
    }

    /**
    * connect the Scanner object to a file we'll be reading from
    */

    public Scanner connect_scanner_to_file(Scanner in)
    {
        File file = new File(this.filename);
        try
        {
            in = new Scanner(file).useDelimiter(DELIMITER); // can be edited, must be something that noone will type in
        }
        catch(Exception e)
        {
            System.out.println(e); // change this
        }
        return in;
    }

    /**
    * connect the FileWriter object to the file we're appending to
    */

    public FileWriter connect_filewriter_to_file(FileWriter out)
    {
        File file = new File(this.filename);
        try
        {
            out = new FileWriter(file, true); // set it to append to a file
        }
        catch(Exception e)
        {
            System.out.println(e); // change this too
        }
        return out;
    }

    public abstract E read(String query) throws Exception;
    public abstract void write(E data) throws Exception;

}
