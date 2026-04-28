package searchEngine;

import java.util.Date;

/**
 * Represents a crawled web page.
 */
public class WebPage {

    private String url;
    private String title;
    private String contentSnippet;
    private Date lastCrawledAt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentSnippet() {
        return contentSnippet;
    }

    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }

    public Date getLastCrawledAt() {
        return lastCrawledAt;
    }

    public void setLastCrawledAt(Date lastCrawledAt) {
        this.lastCrawledAt = lastCrawledAt;
    }
}
