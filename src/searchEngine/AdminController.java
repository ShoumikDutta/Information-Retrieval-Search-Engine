package searchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Admin controller.
 *
 * Also acts as an Observer for SearchIndex updates (Observer pattern).
 */
public class AdminController implements IndexObserver {

    private final List<SystemConfig> configs = new ArrayList<>();
    private final List<AdminAuditLog> auditLogs = new ArrayList<>();
    private final String adminUserName;

    public AdminController(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public AdminController() {
		this.adminUserName = "NULL";
		// TODO Auto-generated constructor stub
    	
	}

	public void updateCrawlLimit(String key, String value) {
        SystemConfig config = new SystemConfig();
        config.setConfigId("CFG-" + (configs.size() + 1));
        config.setKey(key);
        config.setValue(value);
        config.setDescription("Updated by admin " + adminUserName);
        configs.add(config);

        AdminAuditLog log = new AdminAuditLog();
        log.setLogId("LOG-" + (auditLogs.size() + 1));
        log.setAction("Updated crawl config: " + key + "=" + value);
        log.setTimestamp(new Date());
        log.setAdminUser(adminUserName);
        log.setDetails("Config applied but index not yet rebuilt.");
        auditLogs.add(log);
    }

    public List<SystemConfig> getConfigs() {
        return new ArrayList<>(configs);
    }

    public List<AdminAuditLog> getAuditLogs() {
        return new ArrayList<>(auditLogs);
    }

    /**
     * Observer callback: called by SearchIndex when the index is updated.
     */
    @Override
    public void onIndexUpdated(SearchIndex index) {
        AdminAuditLog log = new AdminAuditLog();
        log.setLogId("LOG-" + (auditLogs.size() + 1));
        log.setAction("Index updated to version " + index.getVersion());
        log.setTimestamp(index.getLastUpdatedAt() != null ? index.getLastUpdatedAt() : new Date());
        log.setAdminUser(adminUserName);
        log.setDetails("IndexId=" + index.getIndexId());
        auditLogs.add(log);
    }

    /**
     * Convenience method to register this admin as observer.
     */
    public void registerAsObserver(SearchIndex index) {
        index.addObserver(this);
    }

	public SystemConfig getSystemConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
