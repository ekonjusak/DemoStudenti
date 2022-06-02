package demo.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.app.student.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.valueOf;
import static jdk.nashorn.internal.runtime.JSType.toInt32;
import static jdk.nashorn.internal.runtime.JSType.toNumber;

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
        String red = "bla3";
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

    static public StudentModel createStudent(StudentModel sm ) throws ClassNotFoundException, SQLException {

        Connection conn = SqlConnection.getConnection();

        String query = "insert into student( id, email, ime, mentorid, oib, phonenumber) values(" + sm.getId() + ",'" + sm.getEmail() + "','" + sm.getIme() + "'," + sm.getMentorid() + "," + sm.getOib() + ",'" + sm.getPhonenumber() + "');";
        Statement st = conn.createStatement();
        st.executeUpdate(query); //  query does not return ResultSet

        String query2 = "Select * from student where id = "+sm.getId()+";";
        ResultSet resultSet = st.executeQuery(query2);

        StudentModel response = new StudentModel(toInt32(resultSet.getString("id")), resultSet.getString("email"), resultSet.getString("ime"), toNumber(resultSet.getString("mentorid")), toNumber(resultSet.getString("oib")), resultSet.getString("phonenumber"));

        return response;
    }
}
