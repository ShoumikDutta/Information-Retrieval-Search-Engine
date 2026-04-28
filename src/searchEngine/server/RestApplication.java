package searchEngine.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import searchEngine.rest.SearchResource;
import searchEngine.rest.HelpRequestResource;

public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(SearchResource.class);
        classes.add(HelpRequestResource.class);
        return classes;
    }
}