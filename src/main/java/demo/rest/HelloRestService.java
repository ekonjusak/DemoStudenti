package demo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloRestService {
    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        System.out.println("ovdje nesto napisi  223");
        return Response.status(200).entity("hello 223 ").build();
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
}