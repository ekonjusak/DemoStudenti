package demo.app.database.dao;

import demo.app.database.connector.SqliteConnector;
import demo.app.dto.StudentDto;
import demo.app.mgmt.StudentMgmt;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;
import static org.slf4j.LoggerFactory.getLogger;

public class StudentDao {
    private static final Logger logger = getLogger(StudentDao.class);

    public ArrayList<StudentDto> getAllStudent() throws Exception {
        logger.debug("Starting method getAllStudent");
        Connection conn = SqliteConnector.getConnection();
        ArrayList<StudentDto> allStudents = new ArrayList<>();

        String query = "Select * From students";
        try{
            logger.debug("executing query: "+ query);
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);
            logger.debug("query: "+ query + "resultSet:"+ rst);
            while(rst.next())
            {
                StudentDto tmp = new StudentDto(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email"), rst.getInt("mentor_id")) ;
                allStudents.add(tmp) ;
            }
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("Query is not executed");
        }
        logger.debug("Ending method getAllStudent");
        return allStudents;
    }

    public StudentDto create(StudentDto sd) throws Exception {
        logger.debug(" Starting method create");
        Statement st = null;
        ResultSet resultSet = null;
        String query = "insert into students( name, oib, mobile_phone, email, mentor_id) values('" + sd.getName() + "','" + sd.getOib() + "','" + sd.getMobilePhone() + "','" + sd.getEmail() + "'," + sd.getMentorId() + ");";

        try{
            logger.debug("executing query: "+ query);
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("Query can not be executed 1");
        }

        String query2 = "Select * from students where oib = '"+sd.getOib()+"';";
        try {
            logger.debug("executing query: "+ query);
            resultSet = st.executeQuery(query2);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query2);
            throw new Exception("Query can not be executed 2");
        }

        StudentDto response = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        logger.debug("Ending method create");
        return response;
    }

    public boolean delete(Integer studentId) throws Exception {
        logger.debug("Starting method delete");
        Statement st = null;
        String query = "delete from students where id = "+studentId+";";
        try{
            logger.debug("executing query: "+ query);
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("Query can not be executed");
        }
        logger.debug("Ending method delete");
        // vrati broj obrisanih redaka
        return true;
    }
    public StudentDto update(Integer studentId,StudentDto studentdto) throws Exception {
        logger.debug("Starting method update");
        Statement st = null;
        ResultSet resultSet = null;
        StudentDto StudentFromDbForUpdate = null;
        StudentDto response = null;
        try{
            st = SqliteConnector.getConnection().createStatement();
        }catch(Exception e){
            throw e;
        }

        String query = "Select * from students where id = '"+studentId+"';";
        try {
            logger.debug("executing query: "+ query);
            resultSet = st.executeQuery(query);
            StudentFromDbForUpdate = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
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
        String query1 = "update students SET mobile_phone ='"+StudentFromDbForUpdate.getMobilePhone() +"', name = '"+ StudentFromDbForUpdate.getName() +"', email= '"+StudentFromDbForUpdate.getEmail()+"' , mentor_id = "+StudentFromDbForUpdate.getMentorId()+", oib ='"+StudentFromDbForUpdate.getOib()+"' where id = "+StudentFromDbForUpdate.getId()+";";
        try{
            logger.debug("executing query: "+ query1);
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query1);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query1);
            throw new Exception("Query can not update student.");
        }

        String query2 = "Select * from students where id = "+StudentFromDbForUpdate.getId()+";";
        try {
            logger.debug("executing query: "+ query2);
            resultSet = st.executeQuery(query2);
            response = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
        }catch(Exception e){
            logger.info("query can't be executed: "+ query2);
            throw new Exception("query failed.");
        }
        logger.debug("Ending method update");
        return response;

    }

    public boolean isIdExist(Integer studentId) throws Exception {
        logger.debug("Starting method isIdExist");
        Statement st = null;
        ResultSet resultSet = null;

        String query = "Select * from students where id = "+studentId+";";
        try{
            logger.debug("executing query: "+ query);
            st = SqliteConnector.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            StudentDto sd = new StudentDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"), toInt32(resultSet.getString("mentor_id")));
            System.out.println("student with id "+studentId+" is in DB");
            logger.debug("Ending method isIdExist");
            return true; // id exist

        }catch(Exception e){
            logger.info("query can't be executed: "+ query + ". id: "+ studentId +" does not exist in DB");
            System.out.println("id: "+ studentId +" does not exist in DB");
            logger.debug("Ending method isIdExist");
            return false; // student id does not exist
        }
    }


}
