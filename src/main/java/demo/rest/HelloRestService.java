package demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloRestService {
    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        System.out.println("ovdje nesto napisi  223");
        return Response.status(200).entity("hello 223 ").build();
    }
    // post - u log upisi 'pozvo si me'
}