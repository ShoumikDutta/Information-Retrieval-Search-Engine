package searchEngine;

/**
 * Concrete factory that creates normal web search results.
 */
public class DefaultSearchResultFactory extends SearchResultFactory {

    @Override
    public SearchResult createResult(WebPage page, SearchQuery query) {
        WebSearchResult result = new WebSearchResult();
        result.setResultId("RES-" + System.currentTimeMillis());
        result.setTitle(page.getTitle());
        result.setSnippet("Result for '" + query.getQueryText() + "' from " + page.getUrl());
        result.setRankScore(1.0);
        result.setWebPage(page);
        return result;
    }
}
