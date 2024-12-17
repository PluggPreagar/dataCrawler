package org.ppp.handler;

import org.ppp.Session;

import java.util.List;

public class UrlHttps extends Handler{

    public UrlHttps() {
        super( UrlHttps.class);
    }

    @Override
    public List<List<String>> load(String query, Session session) {
        return super.load(query, session);
    }

    @Override
    public void save(List<List<String>> cells, String query, Session session) {
        super.save(cells, query, session);


    }

}
