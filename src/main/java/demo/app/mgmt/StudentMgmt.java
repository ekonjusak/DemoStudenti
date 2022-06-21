package demo.app.mgmt;

import demo.app.database.dao.StudentDao;
import demo.app.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;


public class StudentMgmt {

    private static final Logger logger = getLogger(StudentMgmt.class);

    public ArrayList<StudentDto> getAllStudent() throws Exception {
        logger.debug("Starting method getAllStudent");

        ArrayList<StudentDto> allStudents = null;
        try{
            StudentDao studentdao = new StudentDao();
            allStudents = studentdao.getAllStudent();
        }catch(Exception e){
            logger.info("Exception in method getAllStudent: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method getAllStudent");
        return allStudents;
    }


    public StudentDto createStudent(StudentDto stDto) throws Exception {
        logger.debug("Starting method createStudent");
        StudentDto response = null;
        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.create(stDto);
        }catch(Exception e){
            logger.info("Exception in method createStudent: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method createStudent");
        return response;
    }

    public boolean deleteStudent(Integer studentId) throws Exception {
        logger.debug("Starting method deleteStudent");
        // provjeri jel postoji id
        boolean response;
        StudentDao studentdao = new StudentDao();
        if(studentdao.isIdExist(studentId)){
            response = studentdao.delete(studentId);
        }else{
            logger.info("Student does not exist in DB. ");
            throw new Exception("Student does not exist in DB.");
        }
        logger.debug("Ending method deleteStudent");
        return response;
    }

    public StudentDto updateStudent(Integer studentId,StudentDto stDto) throws Exception {
        logger.debug("Starting method updateStudent");
        StudentDto response = null;
        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.update(studentId,stDto);
        }catch(Exception e){
            logger.info("Error in method updateStudent: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method updateStudent");
        return response;
    }
}
