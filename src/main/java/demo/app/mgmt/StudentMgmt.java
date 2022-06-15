package demo.app.mgmt;

import demo.app.database.dao.StudentDao;
import demo.app.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class StudentMgmt {

    private static Logger logger = LoggerFactory.getLogger(StudentMgmt.class);

    public ArrayList<StudentDto> getAllStudent() throws Exception {
        logger.debug("in method getAllStudent");
        ArrayList<StudentDto> allStudents = null;
        try{
            StudentDao studentdao = new StudentDao();
            allStudents = studentdao.getAllStudent();
        }catch(Exception e){
            logger.debug("some problem");
            throw new Exception("Query can't be executed.");
        }
        return allStudents;
    }


    public StudentDto createStudent(StudentDto stDto) throws Exception {

        StudentDto response = null;
        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.create(stDto);
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }
        return response;
    }

    public boolean deleteStudent(Integer studentId) throws Exception {

        // provjeri jel postoji id
        StudentDao studentdao = new StudentDao();
        boolean response = studentdao.delete(studentId);
        return response;
    }

    public StudentDto updateStudent(Integer studentId,StudentDto stDto) throws Exception {

        StudentDto response = null;
        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.update(studentId,stDto);
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }

        return response;
    }
}
