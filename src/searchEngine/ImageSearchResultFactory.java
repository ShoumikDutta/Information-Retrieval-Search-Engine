package searchEngine;

/**
 * Concrete factory that creates image search results.
 */
public class ImageSearchResultFactory extends SearchResultFactory {

    @Override
    public SearchResult createResult(WebPage page, SearchQuery query) {
        ImageSearchResult result = new ImageSearchResult();
        result.setResultId("IMG-" + System.currentTimeMillis());
        result.setTitle("Image from " + page.getUrl());
        result.setSnippet("Image result for '" + query.getQueryText() + "'");
        result.setRankScore(1.0);
        result.setWebPage(page);
        return result;
    }
}
