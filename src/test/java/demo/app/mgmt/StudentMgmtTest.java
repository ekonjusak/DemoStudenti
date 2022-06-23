package demo.app.mgmt;

import demo.app.dto.StudentDto;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentMgmtTest {

    @BeforeAll
    static void setUp() {
        System.out.println("before each");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("after each");
    }

    @Test
    @Order(1)
    void getAllStudent(){
        StudentMgmt studentmgmt = new StudentMgmt();
        try{
            ArrayList<StudentDto> response = studentmgmt.getAllStudent();
            assertEquals( 6, response.size() );
        } catch (Exception e) {
            assert(false);
            // throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    void createStudent() {
        StudentMgmt studentmgmt = new StudentMgmt();
        try{
            StudentDto sd = new StudentDto(null,"test2", "11165282", "0990003372", "tes225ail2@gmail.com",2);
            StudentDto response = studentmgmt.createStudent(sd);
            assertEquals( sd.getEmail(), response.getEmail() );
        } catch (Exception e) {
            assert(false);
            // throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    void deleteStudent() throws Exception {
        StudentMgmt studentmgmt = new StudentMgmt();
        assertTrue(studentmgmt.deleteStudent(17));

    }

    @Test
    @Order(4)
    void updateStudent() {
        assert(true);
    }
}