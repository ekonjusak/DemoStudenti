package demo.database;

import demo.app.model.student.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;

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


}
