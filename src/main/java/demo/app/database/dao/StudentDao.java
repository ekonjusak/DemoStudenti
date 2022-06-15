package demo.app.database.dao;

import demo.app.database.connector.SqliteConnector;
import demo.app.dto.StudentDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;

public class StudentDao {

    public ArrayList<StudentDto> getAllStudent() throws Exception {

        Connection conn = SqliteConnector.getConnection();
        ArrayList<StudentDto> allStudents = new ArrayList<>();

        String query = "Select * From students";
        try{
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);

            while(rst.next())
            {
                StudentDto tmp = new StudentDto(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email"), rst.getInt("mentor_id")) ;
                allStudents.add(tmp) ;
            }
        }catch(Exception e){
            throw new Exception("Query is not executed");
        }

        return allStudents;
    }

    public StudentDto create(StudentDto sd) throws Exception {

        Statement st = null;
        ResultSet resultSet = null;
        String query = "insert into students( name, oib, mobile_phone, email, mentor_id) values('" + sd.getName() + "','" + sd.getOib() + "','" + sd.getMobilePhone() + "','" + sd.getEmail() + "'," + sd.getMentorId() + ");";

        try{
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            System.out.println("Query 1 nije dobar");
            throw new Exception("Query can not be executed 1");
        }

        String query2 = "Select * from students where oib = '"+sd.getOib()+"';";
        try {
            resultSet = st.executeQuery(query2);
        }catch(Exception e){
            System.out.println("Query 2 nije dobar");
            throw new Exception("Query can not be executed 2");
        }

        StudentDto response = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));

        return response;
    }

    public boolean delete(Integer studentId) throws Exception {

        Statement st = null;
        String query = "delete from students where id = '"+studentId+"';";
        try{
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            throw new Exception("Query can not be executed");
        }
        // vrati broj obrisanih redaka
        return true;
    }
    public StudentDto update(Integer studentId,StudentDto studentdto) throws Exception {

        Statement st = null;
        ResultSet resultSet = null;
        StudentDto StudentFromDbForUpdate = null;
        StudentDto response = null;
        try{
            st = SqliteConnector.getConnection().createStatement();
        }catch(Exception e){
            throw e;
        }

        String query = "Select * from students where id = "+studentId+";";
        try {
            resultSet = st.executeQuery(query);
            // promjeni naziv studentUpade
            StudentFromDbForUpdate = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        }catch(Exception e){
            throw new Exception("query failed. Student with id does not exist.");
        }

        if(studentdto.getName()!=null){
            StudentFromDbForUpdate.setName(studentdto.getName());
        }
        if(studentdto.getMobilePhone()!=null){
            StudentFromDbForUpdate.setMobilePhone(studentdto.getMobilePhone());
        }
        if(studentdto.getEmail()!=null){
            StudentFromDbForUpdate.setEmail(studentdto.getEmail());
        }
        if(studentdto.getMentorId()!=null){
            StudentFromDbForUpdate.setMentorId(studentdto.getMentorId());
        }
        try{
            String query1 = "update students SET mobile_phone ='"+StudentFromDbForUpdate.getMobilePhone() +"', name = '"+ StudentFromDbForUpdate.getName() +"', email= '"+StudentFromDbForUpdate.getEmail()+"' , mentor_id = "+StudentFromDbForUpdate.getMentorId()+", oib ='"+StudentFromDbForUpdate.getOib()+"' where id = "+StudentFromDbForUpdate.getId()+";";
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query1);
        }catch(Exception e){
            System.out.println("Update nije prosao");
            throw new Exception("Query can not update student.");
        }

        String query2 = "Select * from students where id = "+studentdto.getId()+";";
        try {
            resultSet = st.executeQuery(query2);
            response = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        }catch(Exception e){
            throw new Exception("query failed.");
        }
        return response;

    }

    public boolean isOibUnique(String studentOib) throws Exception {

        Statement st = null;
        ResultSet resultSet = null;

        // check is student in DB
        // oib exist, oib does not exist, oib in db
        String query = "Select * from students where oib = '"+studentOib+"';";
        try{
            st = SqliteConnector.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            StudentDto sd = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
            System.out.println("student with oib "+sd.getOib()+" is in DB");
            return false; // student is not unique

        }catch(Exception e){
            System.out.println("oib: "+ studentOib +" does not exist in DB");
            return true; // student oib is unique
        }
    }


}
