package tp.groupe2.domain;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/todos")
public class TodoRessource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, la todo!";
    }
}