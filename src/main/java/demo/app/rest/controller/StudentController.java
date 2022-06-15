package demo.app.rest.controller;

import demo.app.mgmt.StudentMgmt;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModelCreateUpdate;
import demo.app.rest.model.StudentModelRead;
import demo.app.rest.model.StudentModelValidationInput;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/")
public class StudentController {
    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
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

            return Response.status(200).entity(studentsList).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModelCreateUpdate sm){
        try{
            StudentDto response= null;
            StudentModelValidationInput sv = new StudentModelValidationInput();
            if(sv.isStudentModelUpdateInputOk(sm)){
                StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());
                StudentMgmt studentmgmt = new StudentMgmt();
                response = studentmgmt.createStudent(studentdto);
            }
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") Integer id){
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            boolean response = studentmgmt.deleteStudent(id);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Integer studentId, StudentModelCreateUpdate sm){

        StudentDto response= null;
        try{
            StudentModelValidationInput sv = new StudentModelValidationInput();
            if(sv.isStudentModelUpdateInputOk(sm)){
                StudentDto studentdto = new StudentDto(null, sm.getName(), sm.getOib(), sm.getMobilePhone(), sm.getEmail(), sm.getMentorId());
                StudentMgmt studentmgmt = new StudentMgmt();
                response = studentmgmt.updateStudent(studentId,studentdto);
            }

            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
