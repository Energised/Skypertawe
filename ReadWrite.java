/**
* ReadWrite.java
* @author Dan Woolsey
*
* Base class for all ReadWrite functionality within Skypertawe
*
*/

import java.util.Scanner;
import java.util.PrintWriter;
import java.util.File;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ReadWrite
{
    Scanner in;
    FileWriter out;
    File file;
    Connection conn;
    Statement stmt

    public ReadWrite(String filename)
    {
        this.file = new File(filename);
    }

    public abstract read(){}
    public abstract write(){}

}
