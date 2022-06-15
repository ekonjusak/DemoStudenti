package demo.app.rest.controller;

import demo.app.dto.MentorDto;
import demo.app.dto.StudentDto;
import demo.app.mgmt.MentorMgmt;
import demo.app.mgmt.StudentMgmt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/mentor/")
public class MentorController {

    @GET // This annotation indicates GET request
    @Path("/mentors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(){
        try{
            MentorMgmt mentorMgmt = new MentorMgmt();
            ArrayList<MentorDto> res = mentorMgmt.getAllMentors();
            return Response.status(200).entity(res).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
