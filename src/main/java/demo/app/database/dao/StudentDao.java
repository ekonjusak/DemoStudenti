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

    public String delete(String studentOib) throws Exception {

        Statement st = null;

        String query = "delete from students where oib = '"+studentOib+"';";
        try{
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            throw new Exception("Query can not be executed");
        }

        if(isOibUnique(studentOib)){
            System.out.println("student s oibom: "+studentOib+"je obrisan");
            return "oib: "+ studentOib+" is deleted";
        }else{
            System.out.println("Student is not deleted");
            throw new Exception("Student is not deleted");
        }

    }
    public StudentDto update(StudentDto studentdto) throws Exception {

        Statement st = null;
        ResultSet resultSet = null;
        StudentDto studentUpdate = null;
        StudentDto response = null;
        try{
            st = SqliteConnector.getConnection().createStatement();
        }catch(Exception e){
            throw e;
        }
        // check is student in DB
        if(!isOibUnique(studentdto.getOib())){
            System.out.println("student exist in DB");
        }else{
            System.out.println("Student is not in database");
            throw new Exception("Student is not in database. Student can not be updated.");
        }
        // update logic
        // proba 1

        String query = "Select * from students where oib = '"+studentdto.getOib()+"';";
        try {
            resultSet = st.executeQuery(query);
            studentUpdate = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        }catch(Exception e){
            throw new Exception("query failed.");
        }

        if(studentdto.getName()!=null){
            studentUpdate.setName(studentdto.getName());
        }
        if(studentdto.getMobilePhone()!=null){
            studentUpdate.setMobilePhone(studentdto.getMobilePhone());
        }
        if(studentdto.getEmail()!=null){
            studentUpdate.setEmail(studentdto.getEmail());
        }
        if(studentdto.getMentorId()!=null){
            studentUpdate.setMentorId(studentdto.getMentorId());
        }
        try{
            String query1 = "update students SET mobile_phone ='"+studentUpdate.getMobilePhone() +"', name = '"+ studentUpdate.getName() +"', email= '"+studentUpdate.getEmail()+"' , mentor_id = "+studentUpdate.getMentorId()+" where id = "+studentUpdate.getId()+";";
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query1);
        }catch(Exception e){
            System.out.println("Update nije prosao");
            throw new Exception("Query can not update student.");
        }

        String query2 = "Select * from students where oib = '"+studentdto.getOib()+"';";
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
