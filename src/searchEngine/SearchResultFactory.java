package searchEngine;

/**
 * Factory Method pattern:
 * Creator that defines the factory method for search results.
 */
public abstract class SearchResultFactory {

    /**
     * Factory method that creates a SearchResult for a given page + query.
     */
    public abstract SearchResult createResult(WebPage page, SearchQuery query);

    public String formatResult(SearchResult result) {
        return result.getTitle() + " - " + result.getSnippet();
    }
}
