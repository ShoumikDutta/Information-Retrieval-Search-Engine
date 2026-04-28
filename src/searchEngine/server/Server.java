package searchEngine.server;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

    public static final String BASE_URI = "http://localhost:8088/demo/";

    public static void main(String[] args) {

        ResourceConfig config = ResourceConfig.forApplication(new RestApplication());
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI), config);

        System.out.println("Server started at " + BASE_URI);
    }
}
