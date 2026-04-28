package searchEngine;

import java.util.Date;

/**
 * Simple authentication controller (very simplified for the lab).
 */
public class AuthController {

    public Session login(User user, String passwordPlaintext) {
        // Fake check: in a real system you would hash & compare, here we just accept
        Session session = new Session();
        session.setSessionId("S-" + System.currentTimeMillis());
        session.setUser(user);
        session.setStartedAt(new Date());
        // expires in one hour for demo purposes
        session.setExpiresAt(new Date(System.currentTimeMillis() + 3600_000));
        session.setGuest(false);
        return session;
    }

    public Session createGuestSession() {
        Session session = new Session();
        session.setSessionId("GUEST-" + System.currentTimeMillis());
        session.setUser(null);
        session.setStartedAt(new Date());
        session.setExpiresAt(new Date(System.currentTimeMillis() + 3600_000));
        session.setGuest(true);
        return session;
    }
}
