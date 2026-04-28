package searchEngine;

/**
 * Observer pattern:
 * Observer that gets notified when the search index is updated.
 */
public interface IndexObserver {
    void onIndexUpdated(SearchIndex index);
}
