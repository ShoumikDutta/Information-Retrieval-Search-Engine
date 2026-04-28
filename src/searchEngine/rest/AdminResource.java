package searchEngine.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import searchEngine.AdminController;
import searchEngine.SystemConfig;

@Path("/api/admin/config")
public class AdminResource {

    private AdminController adminController = new AdminController();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SystemConfig getConfig() {
        return adminController.getSystemConfig();
    }
}
