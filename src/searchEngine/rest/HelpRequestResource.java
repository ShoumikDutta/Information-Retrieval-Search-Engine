package searchEngine.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import searchEngine.HelpController;
import searchEngine.HelpRequest;

@Path("/api/helpRequests")
public class HelpRequestResource {

    private HelpController helpController = new HelpController();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelpRequest get(@PathParam("id") String id) {
        return helpController.getHelpRequest(id);
    }
}

