package demo.database;

import demo.app.student.StudentModel;

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
    static public ArrayList<StudentModel> getAllStudent() throws ClassNotFoundException, SQLException {

        Connection conn = SqlConnection.getConnection();
        System.out.println("u metodi");
        ArrayList<StudentModel> allStudents = new ArrayList<>();

        String query = "Select * From students";
        Statement st = conn.createStatement();
        ResultSet rst = st.executeQuery(query);

        while(rst.next())
        {
            StudentModel tmp = new StudentModel(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email"), rst.getInt("mentor_id")) ;
            allStudents.add(tmp) ;
            System.out.println(rst.getString("id")+" "+rst.getString("oib"));
        }

        return allStudents;
    }

    static public StudentModel createStudent(StudentModel sm ) throws ClassNotFoundException, SQLException {

        Connection conn = SqlConnection.getConnection();
        // insert without id
        String query = "insert into students( name, oib, mobile_phone, email, mentor_id) values('" + sm.getName() + "','" + sm.getOib() + "','" + sm.getMobilePhone() + "','" + sm.getEmail() + "'," + sm.getMentorId() + ");";
        Statement st = conn.createStatement();
        st.executeUpdate(query); //  query does not return ResultSet

        String query2 = "Select * from students where oib = '"+sm.getOib()+"';";
        ResultSet resultSet = st.executeQuery(query2);

        StudentModel response = new StudentModel(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));

        return response;
    }
}
