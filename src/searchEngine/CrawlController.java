package searchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for triggering crawls and updating the index.
 */
public class CrawlController {

    private final SearchIndex searchIndex;
    private final List<CrawlJob> jobs = new ArrayList<>();

    public CrawlController(SearchIndex searchIndex) {
        this.searchIndex = searchIndex;
    }

    public CrawlJob runCrawl(List<WebPage> pages) {
        CrawlJob job = new CrawlJob();
        job.setJobId("JOB-" + (jobs.size() + 1));
        job.setStartedAt(new Date());
        job.setStatus("RUNNING");
        jobs.add(job);

        searchIndex.updateIndex(pages);

        job.setEndedAt(new Date());
        job.setStatus("COMPLETED");
        return job;
    }

    public List<CrawlJob> getJobs() {
        return new ArrayList<>(jobs);
    }
}
