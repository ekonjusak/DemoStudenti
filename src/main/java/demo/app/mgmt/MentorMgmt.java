package demo.app.mgmt;

import demo.app.database.dao.MentorDao;
import demo.app.dto.MentorDto;
import org.slf4j.Logger;
import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;

public class MentorMgmt {

    private static final Logger logger = getLogger(MentorMgmt.class);
    public ArrayList<MentorDto> getAllMentors() throws Exception {
        logger.debug("Starting method getAllMentors");
        ArrayList<MentorDto> allMentors = null;
        try{
            MentorDao mentordao = new MentorDao();
            allMentors = mentordao.getAllMentors();
        }catch(Exception e){
            logger.info("Exception in method getAllMentors: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method getAllMentors");
        return allMentors;
    }

    public MentorDto createMentor(MentorDto mtDto) throws Exception {
        logger.debug("Starting method createMentor");
        MentorDto response = null;
        try{
            MentorDao mentordao = new MentorDao();
            response = mentordao.create(mtDto);
        }catch(Exception e){
            logger.info("Exception in method createMentor: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method createMentor");
        return response;
    }

    public boolean deleteMentor(Integer mentorId) throws Exception {
        logger.debug("Starting method deleteMentor");
        // provjeri jel postoji id
        boolean response;
        MentorDao mentordao = new MentorDao();
        if(mentordao.isIdExist(mentorId)){
            response = mentordao.delete(mentorId);
        }else{
            logger.info("Student does not exist in DB. ");
            throw new Exception("Student does not exist in DB.");
        }
        logger.debug("Ending method deleteMentor");
        return response;
    }

    public MentorDto updateMentor(Integer mentorId,MentorDto mtDto) throws Exception {
        logger.debug("Starting method updateMentor");
        MentorDto response = null;
        try{
            MentorDao mentordao = new MentorDao();
            response = mentordao.update(mentorId,mtDto);
        }catch(Exception e){
            logger.info("Error in method updateMentor: " , e);
            throw new Exception("Query can't be executed.");
        }
        logger.debug("Ending method updateMentor");
        return response;
    }
}
