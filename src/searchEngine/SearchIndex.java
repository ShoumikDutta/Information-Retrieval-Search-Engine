package searchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Central search index.
 *
 * - Uses Factory Method (SearchResultFactory) to create SearchResult objects.
 * - Acts as Subject in the Observer pattern and notifies IndexObserver instances
 *   whenever the index is updated.
 */
public class SearchIndex {

    private String indexId;
    private int version;
    private Date lastUpdatedAt;

    private final List<IndexEntry> entries = new ArrayList<>();
    private final List<SystemConfig> configs = new ArrayList<>();
    private final List<IndexObserver> observers = new ArrayList<>();

    private SearchResultFactory resultFactory;

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public int getVersion() {
        return version;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public List<IndexEntry> getEntries() {
        return new ArrayList<>(entries);
    }

    public List<SystemConfig> getConfigs() {
        return new ArrayList<>(configs);
    }

    public void addConfig(SystemConfig config) {
        configs.add(config);
    }

    public SearchResultFactory getResultFactory() {
        return resultFactory;
    }

    public void setResultFactory(SearchResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }

    // Observer management

    public void addObserver(IndexObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(IndexObserver observer) {
        observers.remove(observer);
    }

    private void notifyIndexUpdated() {
        for (IndexObserver observer : observers) {
            observer.onIndexUpdated(this);
        }
    }

    /**
     * Update the index with a list of crawled pages.
     * For each page we create a simple IndexEntry.
     */
    public void updateIndex(List<WebPage> pages) {
        for (WebPage page : pages) {
            IndexEntry entry = new IndexEntry();
            entry.setEntryId("IDX-" + System.currentTimeMillis() + "-" + entries.size());
            entry.setPage(page);
            // Very basic "keywords": just split title by spaces
            if (page.getTitle() != null) {
                String[] tokens = page.getTitle().toLowerCase().split("\\s+");
                List<String> keywords = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.isEmpty()) {
                        keywords.add(t);
                    }
                }
                entry.setKeywords(keywords);
            }
            entries.add(entry);
        }

        version++;
        lastUpdatedAt = new Date();

        // Notify observers that the index changed
        notifyIndexUpdated();
    }

    /**
     * Simple search: scan all entries whose keywords contain a token from the query
     * and create SearchResult objects via the SearchResultFactory.
     */
    public List<SearchResult> search(SearchQuery query) {
        List<SearchResult> results = new ArrayList<>();
        if (resultFactory == null) {
            return results;
        }
        String q = query.getQueryText() == null ? "" : query.getQueryText().toLowerCase();
        String[] queryTokens = q.split("\\s+");

        for (IndexEntry entry : entries) {
            List<String> keywords = entry.getKeywords();
            if (keywords == null) {
                continue;
            }
            boolean match = false;
            for (String token : queryTokens) {
                if (keywords.contains(token)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                SearchResult result = resultFactory.createResult(entry.getPage(), query);
                results.add(result);
            }
        }
        return results;
    }
}
