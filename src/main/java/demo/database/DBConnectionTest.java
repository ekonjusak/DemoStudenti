package demo.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.app.student.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.valueOf;

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

    static public ResultSet createStudent(StudentModel sm ) throws ClassNotFoundException, JsonProcessingException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = SqlConnection.getConnection();
        ResultSet resultSet = null;
        // pogledaj data type
        try
        {
            String query = "insert into student( id, email, ime, mentorid, oib, phonenumber) values(" + sm.getId() +",'"+ sm.getEmail() +"','"+ sm.getIme()+"',"+ sm.getMentorid() +","+ sm.getOib()+",'"+ sm.getPhonenumber()+"');";
            // String query = "insert into student( id, email, ime, mentorid, oib, phonenumber) values("+ 21 +", 'moj.mail@gmail.com','ime studenta',"+ 2 +","+12365478+",'0915425874');";
            Statement st = conn.createStatement();
            st.executeQuery(query);

            String query2 = "Select * from student where id = "+sm.getId() + ";";
            resultSet = st.executeQuery(query2);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return (resultSet);
    }
}
