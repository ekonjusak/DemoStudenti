package demo.app.mgmt;

import demo.app.database.dao.StudentDao;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModel;

import java.util.ArrayList;


public class StudentMgmt {

    public ArrayList<StudentDto> getAllStudent() throws Exception {

        ArrayList<StudentDto> allStudents = null;
        try{
            StudentDao studentdao = new StudentDao();
            allStudents = studentdao.getAllStudent();
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }
        return allStudents;
    }


    public StudentDto createStudent(StudentModel sm) throws Exception {

        StudentDto response = null;

        StudentDao sdao = new StudentDao();
        if(!(sm.getName().matches("[a-zA-Z]{2,20}")) ){
            System.out.println("Name should contains from 2 to 20 letters, a-zA-Z.");
            throw new Exception("Name should contains from 2 to 20 letters, a-zA-Z.");
        } else if (!(sm.getOib().matches("[0-9]{8}")) ){
            System.out.println("Oib should have 8 numbers.");
            throw new Exception("Oib should have 8 integers");
        }else if(!(sm.getMobilePhone().matches("[0-9]{9,10}")) ){
            System.out.println("Mobile phone should have 10 numbers.");
            throw new Exception("Mobile phone should have 10 numbers.");
        }else if(!(sm.getEmail().matches("[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}")) ){
            System.out.println("Email should have @ and .");
            throw new Exception("Email should have @ and .");
        }else if(!(sm.getMentorId().toString().matches("[1-9]")) ){
            System.out.println("Mentor id should be number.");
            throw new Exception("Mentor id should be number.");
        }else if(!sdao.isOibUnique(sm.getOib())){
            System.out.println("Oib is not unique.");
            throw new Exception("Oib is not unique");
        }
        StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());

        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.create(studentdto);
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }

        return response;
    }

    public String deleteStudent(StudentModel sm) throws Exception {

        String response = null;

        StudentDao sd = new StudentDao();
        if (!(sm.getOib().matches("[0-9]{8}")) ){
            System.out.println("Oib should have 8 numbers.");
            throw new Exception("Oib should have 8 integers");
        }else if(sd.isOibUnique(sm.getOib())){
            System.out.println("Oib is not unique or does not exist.");
            throw new Exception("Oib is not unique or does not exist");
        }
        StudentDto sdto = new StudentDto();
        sdto.setOib(sm.getOib());
        StudentDao studentdao = new StudentDao();
        response = studentdao.delete(sdto.getOib());

        return response;
    }

    public StudentDto updateStudent(StudentModel sm) throws Exception {

        StudentDto response = null;

        StudentDao sd = new StudentDao();
        if (!(sm.getOib().matches("[0-9]{8}")) ){
            System.out.println("Oib should have 8 numbers.");
            throw new Exception("Oib should have 8 integers");
        }else if(sd.isOibUnique(sm.getOib())){
            System.out.println("Oib is not unique.");
            throw new Exception("Oib is not unique");
        }
        StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());
        try{
            StudentDao studentdao = new StudentDao();
            response = studentdao.update(studentdto);
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }


        return response;
    }
}
