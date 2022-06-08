package demo.app.mgmt;

import demo.app.database.dao.StudentDao;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModel;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentMgmt {

    public ArrayList<StudentDto> getAllStudent() throws ClassNotFoundException, SQLException {

        ArrayList<StudentDto> allStudents = StudentDao.getAllStudent();

        return allStudents;
    }


    public StudentDto createStudent(StudentModel sm) throws Exception {

        if(!(sm.getName().matches("[a-zA-Z]{2,20}")) ){
            System.out.println("Name should contains from 2 to 20 letters, a-zA-Z.");
            throw new Exception("Name should contains from 2 to 20 letters, a-zA-Z.");
        } else if (!(sm.getOib().matches("[0-9]{8}")) ){
            System.out.println("Oib should have 8 numbers.");
            throw new Exception("Oib should have 8 integers");
        }else if(!(sm.getMobilePhone().matches("[0-9]{9,10}")) ){
            System.out.println("Mobile phone should have 10 numbers.");
            throw new Exception("Mobile phone should have 10 numbers.");
        }else if(!(sm.getEmail().matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}")) ){
            System.out.println("Email should have @ and .");
            throw new Exception("Email should have @ and .");
        }else if(!(sm.getMentorId().toString().matches("[1-30]]")) ){ // popravi
            System.out.println("Mentor id should be number.");
            throw new Exception("Mentor id should be number.");
        }

        // student model --> DTOStudent
        // provjeris errore i inpute
        // ovo mi se ne sviđa
        StudentDto response = null;
        try{
            response = StudentDao.create(sm);
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }

        return response;
    }
}
