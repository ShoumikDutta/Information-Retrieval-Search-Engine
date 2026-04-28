package searchEngine;

import searchEngine.SearchController;
import searchEngine.SearchIndex;
import searchEngine.SearchResult;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for handling user search requests.
 */
public class SearchController {

    private final SearchIndex searchIndex;
  //  private SearchController searchController =
    //        new SearchController(new SearchIndex());

    public SearchController() {
		this.searchIndex = new SearchIndex();
		// TODO Auto-generated constructor stub
	}
    public SearchController(SearchIndex searchIndex) {
        this.searchIndex = searchIndex;
    }



	public List<SearchResult> search(String queryText) {
        SearchQuery query = new SearchQuery();
        query.setQueryId("Q-" + System.currentTimeMillis());
        query.setQueryText(queryText);
        query.setSubmittedAt(new Date());
        return new ArrayList<>(searchIndex.search(query));
    }
}
