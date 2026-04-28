package searchEngine.rest;

import searchEngine.DefaultSearchResultFactory;
import searchEngine.SearchController;
import searchEngine.SearchIndex;
import searchEngine.SearchResult;
import searchEngine.WebPage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/api/search")
public class SearchResource {

    // shared index + controller for all requests
    private static final SearchIndex index = new SearchIndex();
    private static final SearchController searchController = new SearchController(index);

    // simple request body for POST
    public static class SearchRequest {
        public String queryText;
    }

    // initialize some demo data once
    static {
        index.setIndexId("REST_INDEX");
        index.setResultFactory(new DefaultSearchResultFactory());

        List<WebPage> pages = new ArrayList<>();

        WebPage amdPage = new WebPage();
        amdPage.setUrl("https://example.com/amd");
        amdPage.setTitle("AMD Ryzen CPU overview");
        amdPage.setContentSnippet("Article about AMD Ryzen processors and performance.");
        amdPage.setLastCrawledAt(new Date());
        pages.add(amdPage);

        WebPage intelPage = new WebPage();
        intelPage.setUrl("https://example.com/intel-vs-amd");
        intelPage.setTitle("Intel vs AMD CPU comparison");
        intelPage.setContentSnippet("Comparison of Intel and AMD CPUs.");
        intelPage.setLastCrawledAt(new Date());
        pages.add(intelPage);

        WebPage gpuPage = new WebPage();
        gpuPage.setUrl("https://example.com/gpu");
        gpuPage.setTitle("GPU architecture basics");
        gpuPage.setContentSnippet("Basic explanation of GPU architecture.");
        gpuPage.setLastCrawledAt(new Date());
        pages.add(gpuPage);

        index.updateIndex(pages);
    }

    /**
     * GET /demo/api/search?q=cpu+amd
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SearchResult> getSearch(@QueryParam("q") String queryText) {
        if (queryText == null) {
            queryText = "";
        }
        return searchController.search(queryText);
    }

    /**
     * POST /demo/api/search
     * Body: { "queryText": "cpu amd" }
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSearch(SearchRequest request) {
        if (request == null || request.queryText == null || request.queryText.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("queryText must not be empty")
                    .build();
        }

        List<SearchResult> results = searchController.search(request.queryText);
        return Response.ok(results).build();
    }
}