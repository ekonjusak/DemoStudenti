package demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.app.student.StudentModel;
import demo.database.DBConnectionTest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;

@Path("/")
public class HelloRestService {
    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() throws ClassNotFoundException {
        System.out.println("ovdje nesto napisi  223");
        String res = DBConnectionTest.getAll();
        return Response.status(200).entity(res).build();
    }
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RequestBody createCustomer(RequestBody body) throws JsonProcessingException {

        // vrati response - napravi objekt koji primas i koji vracas
        return body;
    }

    @POST
    @Path("/poststudent")
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

