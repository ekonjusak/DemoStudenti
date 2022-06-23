package demo.app.database.dao;

import demo.app.database.connector.SqliteConnector;
import demo.app.dto.MentorDto;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;
import static org.slf4j.LoggerFactory.getLogger;

public class MentorDao {

    private static final Logger logger = getLogger(MentorDao.class);
    public ArrayList<MentorDto> getAllMentors() throws Exception {

        logger.debug("Starting method getAllMentors");
        Connection conn = SqliteConnector.getConnection();
        ArrayList<MentorDto> allMentors = new ArrayList<>();

        String query = "Select * From mentors";
        try{
            logger.debug("executing query: "+ query);
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(query);
            logger.debug("query: "+ query + "resultSet:"+ rst);
            while(rst.next())
            {
                MentorDto tmp = new MentorDto(rst.getInt("id"), rst.getString("name"),rst.getString("oib"), rst.getString("mobile_phone"), rst.getString("email")) ;
                allMentors.add(tmp) ;
            }
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("Query is not executed");
        }
        logger.debug("Ending method getAllStudent");
        return allMentors;
    }

    public MentorDto create(MentorDto md) throws Exception {
        logger.debug(" Starting method create");
        Statement st;
        ResultSet resultSet;
        String query = "insert into mentors( name, oib, mobile_phone, email) values('" + md.getName() + "','" + md.getOib() + "','" + md.getMobilePhone() + "','" + md.getEmail() + "');";

        try{
            logger.debug("executing query: "+ query);
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("Query can not be executed 1");
        }

        String query2 = "Select * from mentors where oib = '"+md.getOib()+"';";
        try {
            logger.debug("executing query: "+ query);
            resultSet = st.executeQuery(query2);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query2);
            throw new Exception("Query can not be executed 2");
        }

        MentorDto response = new MentorDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"));
        logger.debug("Ending method create");
        return response;
    }

    public boolean delete(Integer mentorId) throws Exception {
        logger.debug("Starting method delete");
        Statement st;
        String query = "delete from mentors where id = "+mentorId+";";
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

    public MentorDto update(Integer mentorId,MentorDto mentordto) throws Exception {
        logger.debug("Starting method update");
        Statement st;
        ResultSet resultSet;
        MentorDto mentorFromDbForUpdate;
        MentorDto response;
        try{
            st = SqliteConnector.getConnection().createStatement();
        }catch(Exception e){
            throw e;
        }

        String query = "Select * from mentors where id = '"+mentorId+"';";
        try {
            logger.debug("executing query: "+ query);
            resultSet = st.executeQuery(query);
            mentorFromDbForUpdate = new MentorDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"));
        }catch(Exception e){
            logger.info("query can't be executed: "+ query);
            throw new Exception("query failed. Student with id does not exist.");
        }

        if(mentordto.getName()!=null){
            mentorFromDbForUpdate.setName(mentordto.getName());
        }
        if(mentordto.getMobilePhone()!=null){
            mentorFromDbForUpdate.setMobilePhone(mentordto.getMobilePhone());
        }
        if(mentordto.getEmail()!=null){
            mentorFromDbForUpdate.setEmail(mentordto.getEmail());
        }

        String query1 = "update mentors SET mobile_phone ='"+mentorFromDbForUpdate.getMobilePhone() +"', name = '"+ mentorFromDbForUpdate.getName() +"', email= '"+mentorFromDbForUpdate.getEmail()+"' , oib ='"+mentorFromDbForUpdate.getOib()+"' where id = "+mentorFromDbForUpdate.getId()+";";
        try{
            logger.debug("executing query: "+ query1);
            st = SqliteConnector.getConnection().createStatement();
            st.executeUpdate(query1);
        }catch(Exception e){
            logger.info("query can't be executed: "+ query1);
            throw new Exception("Query can not update student.");
        }

        String query2 = "Select * from mentors where id = "+mentorFromDbForUpdate.getId()+";";
        try {
            logger.debug("executing query: "+ query2);
            resultSet = st.executeQuery(query2);
            response = new MentorDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"));
        }catch(Exception e){
            logger.info("query can't be executed: "+ query2);
            throw new Exception("query failed.");
        }
        logger.debug("Ending method update");
        return response;

    }


    public boolean isIdExist(Integer mentorId) {
        logger.debug("Starting method isIdExist");
        Statement st;
        ResultSet resultSet;

        String query = "Select * from mentors where id = "+mentorId+";";
        try{
            logger.debug("executing query: "+ query);
            st = SqliteConnector.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            MentorDto sd = new MentorDto(toInt32(resultSet.getString("id")), resultSet.getString("name"), resultSet.getString("oib"), resultSet.getString("mobile_phone"), resultSet.getString("email"));
            System.out.println("mentor with id "+mentorId+" is in DB");
            logger.debug("Ending method isIdExist");
            return true; // id exist

        }catch(Exception e){
            logger.info("query can't be executed: "+ query + ". id: "+ mentorId +" does not exist in DB");
            System.out.println("id: "+ mentorId +" does not exist in DB");
            logger.debug("Ending method isIdExist");
            return false; // student id does not exist
        }
    }
}
