package SessionManager;

public class Session {

    private String sessionId;
    private String username;
    private long loginTime;

    public Session(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
        this.loginTime = System.currentTimeMillis();
    }

    // New constructor to restore session with specific login time
    public Session(String sessionId, String username, long loginTime) {
        this.sessionId = sessionId;
        this.username = username;
        this.loginTime = loginTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public long getLoginTime() {
        return loginTime;
    }

    @Override
    public String toString() {
        return "Session{"
                + "sessionId='" + sessionId + '\''
                + ", username='" + username + '\''
                + ", loginTime=" + loginTime
                + '}';
    }
}
