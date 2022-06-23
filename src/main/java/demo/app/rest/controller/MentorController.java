package demo.app.rest.controller;

import demo.app.dto.MentorDto;
import demo.app.mgmt.MentorMgmt;
import demo.app.rest.model.*;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/mentor/")
public class MentorController {

    private static final Logger logger = getLogger(MentorController.class);
    @GET // This annotation indicates GET request
    @Path("/mentors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMentors(){
        logger.info("Starting GET method getMentors");
        logger.debug("Starting GET method getMentors with no params");
        try{
            MentorMgmt mentorMgmt = new MentorMgmt();
            ArrayList<MentorDto> res = mentorMgmt.getAllMentors();
            // DTO -> REST model
            ArrayList<MentorModelRead> mentorList = new ArrayList<>();
            for (MentorDto re : res) {
                MentorModelRead tempMentorRest = new MentorModelRead(re.getId(), re.getName(), re.getOib(), re.getMobilePhone(), re.getEmail());
                mentorList.add(tempMentorRest);
            }
            logger.debug("Ending method getMentors with Reponse: "+ mentorList);
            return Response.status(200).entity(mentorList).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method getMentors: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMentor(MentorModelCreateUpdate mm){
        logger.info("Starting POST method createMentor");
        logger.debug("Starting method createMentor with input params: "+ mm);
        try{
            MentorDto response= null;
            MentorModelValidationInput sv = new MentorModelValidationInput();
            if(sv.isMentorModelUpdateInputOk(mm)){
                MentorDto mentordto = new MentorDto(null, mm.getName(), mm.getOib(), mm.getMobilePhone(), mm.getEmail());
                MentorMgmt mentormgmt = new MentorMgmt();
                response = mentormgmt.createMentor(mentordto);
            }
            logger.debug("Ending method createMentor with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method createSMentor: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMentor(@PathParam("id") Integer id){
        logger.info("Starting DELETE method deleteMentor");
        logger.debug("Starting method deleteMentor with input params: id= "+ id);
        try{
            MentorMgmt mentormgmt = new MentorMgmt();
            boolean response = mentormgmt.deleteMentor(id);
            logger.debug("Ending method deleteMentor with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method deleteMentor: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMentor(@PathParam("id") Integer mentorId, MentorModelCreateUpdate mm){
        logger.info("Starting PUT method updateMentor");
        logger.debug("Starting method createMentor with input params: id= "+ mentorId + "and body: Mentor model: "+ mm);
        MentorDto response= null;
        try{
            MentorModelValidationInput mv = new MentorModelValidationInput();
            if(mv.isMentorModelUpdateInputOk(mm)){
                MentorDto mentordto = new MentorDto(null, mm.getName(), mm.getOib(), mm.getMobilePhone(), mm.getEmail());
                MentorMgmt mentormgmt = new MentorMgmt();
                response = mentormgmt.updateMentor(mentorId,mentordto);
            }
            logger.debug("Ending method updateMentor with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method updateMentor: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
