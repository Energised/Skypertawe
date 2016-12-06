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

abstract class ReadWrite
{
    Scanner in;
    PrintWriter out;
    String filename;
    Connection conn;
    Statement stmt;

    public ReadWrite(String filename)
    {
        this.filename = filename;
    }

    public abstract void read();
    public abstract void write();

}
