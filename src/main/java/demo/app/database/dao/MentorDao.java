package demo.app.database.dao;

import demo.app.database.connector.SqliteConnector;
import demo.app.dto.MentorDto;
import demo.app.dto.StudentDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MentorDao {

    public ArrayList<MentorDto> getAllMentors() throws Exception {

        Connection conn = SqliteConnector.getConnection();
        ArrayList<MentorDto> allMentors = new ArrayList<>();

        String query = "Select * From mentors";
        try{
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);

            while(rst.next())
            {
                MentorDto tmp = new MentorDto(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email")) ;
                allMentors.add(tmp) ;
            }
        }catch(Exception e){
            throw new Exception("Query is not executed");
        }

        return allMentors;
    }
}
