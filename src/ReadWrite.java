/**
* ReadWrite.java
* @author Dan Woolsey
*
* Base class for all ReadWrite functionality within Skypertawe
*
* NB: Have removed the abstract methods for read() and write() due to
*     being redundant for subclasses which needed more functionality
*
*     When dealing with SQLite; all functions must be able to throw exceptions
*/

package src;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

abstract class ReadWrite<E>
{
    String dbname;
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
    * Connect to the database file
    * @return conn - Connection object to db
    */

    public Connection connect_to_db(Connection conn) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String conn_info = "jdbc:sqlite:" + this.dbname;
        conn = DriverManager.getConnection(conn_info);
        return conn;
    }
}
