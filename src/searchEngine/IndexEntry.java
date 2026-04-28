package searchEngine;

import java.util.List;

/**
 * Represents an entry in the inverted index.
 */
public class IndexEntry {

    private String entryId;
    private WebPage page;
    private List<String> keywords;

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public WebPage getPage() {
        return page;
    }

    public void setPage(WebPage page) {
        this.page = page;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
