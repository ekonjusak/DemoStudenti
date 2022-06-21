package demo.app.rest.controller;

import demo.app.mgmt.StudentMgmt;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModelCreateUpdate;
import demo.app.rest.model.StudentModelRead;
import demo.app.rest.model.StudentModelValidationInput;
import org.slf4j.Logger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/")
public class StudentController {

    private static final Logger logger = getLogger(StudentController.class);
    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        logger.info("Starting GET method getStudents");
        logger.debug("Starting GET method getStudents with no params");
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            // treba novi model koji nije StudentDto, treba novi rest model
            ArrayList<StudentDto> res = studentmgmt.getAllStudent();
            // DTO -> REST model
            ArrayList<StudentModelRead> studentsList = new ArrayList<>();
            for (StudentDto re : res) {
                StudentModelRead tempStudentRest = new StudentModelRead(re.getId(), re.getName(), re.getOib(), re.getMobilePhone(), re.getEmail(), re.getMentorId());
                studentsList.add(tempStudentRest);
            }
            logger.debug("Ending method getStudents with Reponse: "+ studentsList);
            return Response.status(200).entity(studentsList).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method getStudents: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModelCreateUpdate sm){
        logger.info("Starting POST method createStudent");
        logger.debug("Starting method createStudent with input params: "+ sm);
        try{
            StudentDto response= null;
            StudentModelValidationInput sv = new StudentModelValidationInput();
            if(sv.isStudentModelUpdateInputOk(sm)){
                StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());
                StudentMgmt studentmgmt = new StudentMgmt();
                response = studentmgmt.createStudent(studentdto);
            }
            logger.debug("Ending method createStudent with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method createStudent: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") Integer id){
        logger.info("Starting DELETE method deleteStudent");
        logger.debug("Starting method createStudent with input params: id= "+ id);
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            boolean response = studentmgmt.deleteStudent(id);
            logger.debug("Ending method deleteStudent with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method deleteStudent: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Integer studentId, StudentModelCreateUpdate sm){
        logger.info("Starting PUT method updateStudent");
        logger.debug("Starting method createStudent with input params: id= "+ studentId + "and body: Student model: "+ sm);
        StudentDto response= null;
        try{
            StudentModelValidationInput sv = new StudentModelValidationInput();
            if(sv.isStudentModelUpdateInputOk(sm)){
                StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());
                StudentMgmt studentmgmt = new StudentMgmt();
                response = studentmgmt.updateStudent(studentId,studentdto);
            }
            logger.debug("Ending method updateStudent with response: "+ response);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            logger.info("Exception e in method updateStudent: e = "+ e);
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
