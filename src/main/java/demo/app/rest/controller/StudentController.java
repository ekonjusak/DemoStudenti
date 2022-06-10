package demo.app.rest.controller;

import demo.app.database.dao.StudentDao;
import demo.app.mgmt.StudentMgmt;
import demo.app.dto.StudentDto;
import demo.app.rest.model.StudentModel;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/")
public class StudentController {
    @GET // This annotation indicates GET request
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() throws ClassNotFoundException, SQLException {
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            ArrayList<StudentDto> res = studentmgmt.getAllStudent();
            return Response.status(200).entity(res).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModel sm){
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            StudentDto response = studentmgmt.createStudent(sm);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(StudentModel sm){
        // buduci da primam samo oib, zašto imam cijeli objekt StudentModel ?
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            String response = studentmgmt.deleteStudent(sm);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(StudentModel sm){
        // update po oibu da ide ??
        // buduci da primam samo oib, zašto imam cijeli objekt StudentModel ?
        try{
            StudentMgmt studentmgmt = new StudentMgmt();
            StudentDto response = studentmgmt.updateStudent(sm);
            return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            return Response.status(400).entity("exception: "+e).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
