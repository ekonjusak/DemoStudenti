package demo.rest;

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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String createCustomer(@FormParam("name") String name,
                                 @FormParam("address") String address,
                                 @FormParam("phone-number") String phoneNumber) {
        return "name, address, phoneNumber " + name;
    }

    @POST
    @Path("/poststudent")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public ResultSet createStudent(@FormParam("id") String id,
                                   @FormParam("email") String email,
                                   @FormParam("mentorid") Number mentorid,
                                   @FormParam("oib") Number oib,
                                   @FormParam("phonenumber") String phonenumber
                                ) throws ClassNotFoundException {
        ResultSet res = DBConnectionTest.createStudent();

        return res;
    }
}