package demo.app.mgmt;

import demo.app.database.dao.StudentDao;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModel;
import demo.app.database.connector.SqliteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.JSType.toInt32;

public class StudentMgmt {

    static public ArrayList<StudentDto> getAllStudent() throws ClassNotFoundException, SQLException {

        ArrayList<StudentDto> allStudents = StudentDao.getAllStudent();

        return allStudents;
    }


    static public StudentDto createStudent(StudentModel sm) throws ClassNotFoundException, SQLException {

        // provjeris errore i inpute
        // student model mapiraš u DTO
        StudentDto response = StudentDao.create(sm);

        return response;
    }
}
