package demo.app.database.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnector {

    private static Connection conn;

    private void DBConnection(){}

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try
        {
            if(conn==null)
            {
                String url = "jdbc:sqlite:C:/projekti.gitlab/sqldb/dbschool.db";
                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
