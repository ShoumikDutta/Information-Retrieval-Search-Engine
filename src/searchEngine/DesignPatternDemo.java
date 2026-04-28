package searchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Very simple demo of the search engine and the two design patterns:
 *
 * 1) Factory Method
 *    - SearchIndex uses a SearchResultFactory (DefaultSearchResultFactory / ImageSearchResultFactory)
 *      to create different types of SearchResult objects for the same query.
 *
 * 2) Observer
 *    - SearchIndex is the Subject.
 *    - AdminController is an Observer that automatically receives a notification
 *      every time the index is updated (after a crawl).
 *
 * The console output is kept small and clear so the professor can understand it quickly.
 */
public class DesignPatternDemo  {

    public static void main(String[] args) {
        // 1) Create the core objects
        SearchIndex index = new SearchIndex();
        index.setIndexId("MAIN_INDEX");

        // Start with WEB result factory (Factory Method)
        index.setResultFactory(new DefaultSearchResultFactory());

        // Admin listens to index updates (Observer)
        AdminController admin = new AdminController("admin@example.com");
        admin.registerAsObserver(index);

        CrawlController crawlController = new CrawlController(index);
        SearchController searchController = new SearchController(index);

        // 2) Prepare a few example pages to crawl
        List<WebPage> pages = new ArrayList<>();

        WebPage cpuPage = new WebPage();
        cpuPage.setUrl("https://example.com/cpu");
        cpuPage.setTitle("AMD Ryzen CPU overview");
        cpuPage.setContentSnippet("Overview of AMD Ryzen CPUs.");
        cpuPage.setLastCrawledAt(new Date());
        pages.add(cpuPage);

        WebPage hddPage = new WebPage();
        hddPage.setUrl("https://example.com/hdd");
        hddPage.setTitle("PC HDD buying guide");
        hddPage.setContentSnippet("How to choose a hard disk drive.");
        hddPage.setLastCrawledAt(new Date());
        pages.add(hddPage);

        // 3) Run a crawl -> updates index AND notifies Admin (Observer)
        System.out.println("=== STEP 1: Crawl pages (Observer pattern in action) ===");
        crawlController.runCrawl(pages);
        System.out.println("Index version: " + index.getVersion());
        System.out.println();

        // 4) Search using DefaultSearchResultFactory (WEB results)
        System.out.println("=== STEP 2: Search with DefaultSearchResultFactory (Factory Method) ===");
        String queryText = "cpu amd";
        System.out.println("Searching for: \"" + queryText + "\"\n");

        List<SearchResult> webResults = searchController.search(queryText);
        printSearchResults("WEB results", webResults);

        // 5) Switch to ImageSearchResultFactory and run the SAME search again
        System.out.println();
        System.out.println("=== STEP 3: Switch to ImageSearchResultFactory (Factory Method) ===");
        index.setResultFactory(new ImageSearchResultFactory());

        List<SearchResult> imageResults = searchController.search(queryText);
        printSearchResults("IMAGE results", imageResults);

        // 6) Show admin audit logs (created automatically via Observer callbacks)
        System.out.println();
        System.out.println("=== STEP 4: Admin audit logs (Observer pattern) ===");
        for (AdminAuditLog log : admin.getAuditLogs()) {
            System.out.println(
                    log.getTimestamp() + " | " +
                    log.getAction() + " | " +
                    log.getDetails()
            );
        }

        System.out.println();
        System.out.println("=== Demo finished ===");
    }

    /**
     * Helper method to print search results in a simple "search engine" style.
     */
    private static void printSearchResults(String label, List<SearchResult> results) {
        System.out.println(label + " - total results: " + results.size());
        int i = 1;
        for (SearchResult r : results) {
            System.out.println(i + ". " +
                    r.getTitle() + " (" +
                    (r.getWebPage() != null ? r.getWebPage().getUrl() : "no URL") + ")");
            System.out.println("   " + r.getSnippet());
            System.out.println("   id=" + r.getResultId());
            i++;
        }
    }
}
