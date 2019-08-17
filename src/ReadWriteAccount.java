/**
 * ReadWriteAccount.java
 * @author Dan Woolsey
 *
 * FUNCTION LIST:
 *      (y) ReadWriteAccount(String dbname)
 *      (y) CreateAccountTable(Conenction conn, Statement stmt)
 *
 * NB: RUN: java -cp ".:sqlite-jdbc-3.15.1.jar" ReadWriteAccount
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadWriteAccount extends ReadWrite<Account>
{

    // NB: When dealing with SQLite; all function must be able to throw exceptions

    public ReadWriteAccount(String dbname) throws Exception
    {
        super(dbname); // if db doesnt exist, will create new db
        this.stmt = createAccountTable(this.conn, this.stmt);
    }

    /**
    * BUILD THE ACCOUNTS TABLE
    */

    public Statement createAccountTable(Connection conn, Statement stmt) throws Exception
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

    public static void main(String[] args) throws Exception
    {
        ReadWriteAccount rwa = new ReadWriteAccount("data.db");
    }
}
