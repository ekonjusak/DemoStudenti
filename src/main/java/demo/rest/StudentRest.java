package demo.rest;

import demo.app.logic.LgcStudent;
import demo.app.model.student.CreateStudent;
import demo.app.model.student.StudentModel;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/")
public class StudentRest {
    @GET // This annotation indicates GET request
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() throws ClassNotFoundException, SQLException {
        // promjeni imena funkcija hello
        ArrayList<StudentModel> res = LgcStudent.getAllStudent();
        return Response.status(200).entity(res).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(CreateStudent sm){
        // promjeni ime funkcije createStudent
        try{
            StudentModel responseModel = LgcStudent.createStudent(sm);
            return Response.status(200).entity(responseModel).type(MediaType.APPLICATION_JSON).build();
        }catch(Exception e){
            System.out.println("400");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            // return Response.status(400).build();
        }
    }


}
