/**
* ReadWrite.java
* @author Dan Woolsey
*
* Base class for all ReadWrite functionality within Skypertawe
*
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
    String filename;
    Connection conn = null;
    Statement stmt = null;

    public ReadWrite(String filename)
    {
        this.filename = filename;
    }

    public abstract void read(String query) throws Exception;
    public abstract void write(E data) throws Exception;

}
