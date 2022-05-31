package demo.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionTest {

    public static void main(String [] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = SqlConnection.getConnection();

        try
        {
            String query = "Select * From student";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);

            while(rst.next())
            {
                System.out.println(rst.getString("id")+" "+rst.getString("oib"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally
        {
            try{
                conn.close();
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }

    }
    static public String getAll() throws ClassNotFoundException {

        Connection conn = SqlConnection.getConnection();
        String red = "bla";
        System.out.println("u metodi");
        try
        {
            String query = "Select * From student";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);

            while(rst.next())
            {
                System.out.println(rst.getString("id")+" "+rst.getString("oib"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return red;
    }

    static public ResultSet createStudent() throws ClassNotFoundException {

        Connection conn = SqlConnection.getConnection();
        ResultSet rst = null;
        System.out.println("u metodi");
        try
        {
            String query = "insert into student(id, email,ime, mentorid,oib,phonenumber) values(25, 'mail.moj@gmail.com', 2, 12369854, '099587412')";
            Statement st = conn.createStatement();
            rst = st.executeQuery(query);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return rst;
    }
}
