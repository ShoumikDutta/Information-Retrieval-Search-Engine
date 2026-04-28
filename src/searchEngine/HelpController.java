package searchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller for FAQ and help tickets.
 */
public class HelpController {

    private final List<FAQEntry> faqEntries = new ArrayList<>();
    private final List<HelpRequest> helpRequests = new ArrayList<>();

    public HelpController() {
        // Example FAQ entries
        FAQEntry e1 = new FAQEntry();
        e1.setFaqId("FAQ-1");
        e1.setQuestion("How does the search engine rank results?");
        e1.setAnswer("Results are ranked by a simple keyword match for this demo.");
        e1.setCategory("Search");

        FAQEntry e2 = new FAQEntry();
        e2.setFaqId("FAQ-2");
        e2.setQuestion("How can I delete my data?");
        e2.setAnswer("Submit a data deletion request in your account settings.");
        e2.setCategory("Privacy");

        faqEntries.add(e1);
        faqEntries.add(e2);
    }

    public List<FAQEntry> loadFAQ() {
        return new ArrayList<>(faqEntries);
    }

    public HelpRequest createTicket(User user, String subject, String message) {
        HelpRequest request = new HelpRequest();
        request.setTicketId("T-" + (helpRequests.size() + 1));
        request.setUser(user);
        request.setSubject(subject);
        request.setMessage(message);
        request.setStatus("OPEN");
        request.setCreatedAt(new Date());
        helpRequests.add(request);
        return request;
    }

    public List<HelpRequest> getHelpRequests() {
        return new ArrayList<>(helpRequests);
    }

    public HelpRequest getHelpRequest(String id) {
        for (HelpRequest request : helpRequests) {
            if (request.getTicketId().equals(id)) {
                return request;
            }
        }
        return null; // not found
    }
}
