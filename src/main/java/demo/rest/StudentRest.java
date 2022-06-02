package demo.rest;

import demo.app.student.StudentModel;
import demo.database.DBConnectionTest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/student")
public class StudentRest {
    @GET // This annotation indicates GET request
    @Path("/get")
    public Response hello() throws ClassNotFoundException, SQLException {

        ArrayList<StudentModel> res = DBConnectionTest.getAllStudent();
        return Response.status(200).entity(res).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(StudentModel sm){

        try{
            StudentModel responseModel = DBConnectionTest.createStudent(sm);
            return Response.status(200).entity(responseModel).build();
        }catch(Exception e){
            System.out.println("400");
            return Response.status(400).build();
        }
    }
}
