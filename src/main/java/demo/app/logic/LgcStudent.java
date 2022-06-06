package demo.app.logic;

import demo.app.model.student.CreateStudent;
import demo.app.model.student.StudentModel;
import demo.database.SqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;

public class LgcStudent {

    static public ArrayList<StudentModel> getAllStudent() throws ClassNotFoundException, SQLException {

        Connection conn = SqlConnection.getConnection();
        ArrayList<StudentModel> allStudents = new ArrayList<>();

        String query = "Select * From students";
        Statement st = conn.createStatement();
        ResultSet rst = st.executeQuery(query);

        while(rst.next())
        {
            StudentModel tmp = new StudentModel(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email"), rst.getInt("mentor_id")) ;
            allStudents.add(tmp) ;
        }

        return allStudents;
    }


    static public StudentModel createStudent(CreateStudent sm ) throws ClassNotFoundException, SQLException {

        Connection conn = SqlConnection.getConnection();
        // insert without id
        String query = "insert into students( name, oib, mobile_phone, email, mentor_id) values('" + sm.getName() + "','" + sm.getOib() + "','" + sm.getMobilePhone() + "','" + sm.getEmail() + "'," + sm.getMentorId() + ");";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        String query2 = "Select * from students where oib = '"+sm.getOib()+"';";
        ResultSet resultSet = st.executeQuery(query2);

        StudentModel response = new StudentModel(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));

        return response;
    }
}
