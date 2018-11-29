package com.provys.provysdb;

import oracle.ucp.ConnectionLabelingCallback;
import oracle.ucp.jdbc.LabelableConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Labeling callback implementation - switch to required session
 */
class ProvysConnectionLabelingCallback implements ConnectionLabelingCallback {

    private static final Logger LOG = LogManager.getLogger(ProvysConnectionLabelingCallback.class.getName());
    private static final int EXACT_MATCH = 0;
    private static final int SESSION_MATCH = 10;
    private static final int NEW_SESSION = 20;
    private static final int REINIT = 30;

    ProvysConnectionLabelingCallback() {
    }

    @Override
    public int cost(Properties reqLabels, Properties currentLabels) {
        // exact match
        if (reqLabels.equals(currentLabels)) {
            LOG.debug("Exact connection match (equals)");
            return EXACT_MATCH;
        }
        // sessionlog_id matches or no-session connection
        String session1 = reqLabels.getProperty("SESSION_ID");
        if (session1 == null) {
            session1 = "FULL_READONLY";
        }
        String session2 = currentLabels.getProperty("SESSION_ID");
        if (session2 != null && session1.equals(session2)) {
            LOG.debug("Connection match using SESSION_ID");
            return SESSION_MATCH;
        }
        // new (uninitialised) connection
        if (session2 == null) {
            LOG.debug("Uninitialized connection found");
            return NEW_SESSION;
        }
        // connection used for different session / context
        LOG.debug("Connection does not match supplied SESSION_ID");
        return REINIT;
    }

    @Override
    public boolean configure(Properties reqLabels, Object conn) {
        try {
            LabelableConnection lconn = (LabelableConnection) conn;
            Properties unmatchedLabels = lconn.getUnmatchedConnectionLabels(
                    reqLabels);
            for (Map.Entry<Object, Object> label : unmatchedLabels.entrySet()) {
                String key = (String) label.getKey();
                String value = (String) label.getValue();
                if (key.equals("SESSION_ID")) {
                    if (!value.equals("FULL_READONLY")) {
                        throw new RuntimeException("Support for get connection"
                                + " with SESSION_ID not implemented yet");
                    }
                }
                lconn.applyConnectionLabel(key, value);
            }
        } catch (SQLException e) {
            LOG.warn("SQL exception during connection configuration", e);
            return false;
        }
        return true;
    }

}