package demo.app.mgmt;

import demo.app.database.dao.MentorDao;
import demo.app.database.dao.StudentDao;
import demo.app.dto.MentorDto;
import demo.app.dto.StudentDto;

import java.util.ArrayList;

public class MentorMgmt {

    public ArrayList<MentorDto> getAllMentors() throws Exception {

        ArrayList<MentorDto> allMentors = null;
        try{
            MentorDao mentordao = new MentorDao();
            allMentors = mentordao.getAllMentors();
        }catch(Exception e){
            throw new Exception("Query can't be executed.");
        }
        return allMentors;
    }
}
