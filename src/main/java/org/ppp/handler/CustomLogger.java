package org.ppp.handler;

import org.ppp.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CustomLogger {

    Logger LOG = Logger.getLogger( Handler.class.getName() );

    private Session session = null;
    private Map<String,Integer> first = new HashMap<>();

    public CustomLogger(Object o) {
        LOG = Logger.getLogger(o.getClass().getName());
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void syso(String message) {
        System.out.println(message);
    }

    public void logFirst(String key, String message) {
        // output only first 10 times of key
        Integer i = first.get(key);
        if (null == i) {
            i = 0;
        }
        if (i < 10) {
            System.out.println(message);
            first.put(key, i+1);
            if (i == 9) {
                System.out.println(key + " ... (further messages suppressed for key)");
            }
        }
    }

    public void debug(String message) {
        System.out.println(message);
    }

    public void severe(String message) {
        LOG.severe(message);
        syso(message);
    }

    public void severe(Exception e) {
        LOG.severe(e.getMessage());
        syso(e.getMessage());
    }

    public void warning(Exception e) {
        LOG.warning(e.getMessage());
        syso(e.getMessage());
    }

    public void warning(String message) {
        LOG.info(message);
        syso(message);
    }

    public void info(String message) {
        LOG.info(message);
        syso(message);
    }

    public void fine(String message) {
        LOG.fine(message);
        syso(message);
    }
}
