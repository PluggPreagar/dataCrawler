package org.ppp.handler;

import org.ppp.Session;

import java.util.ArrayList;
import java.util.List;

public class Handler extends CustomLogger {

    // add constructor here
    public Handler(Object o) {
        super(o);
    }


    // ----------------- 8< -----------------

    public List<List<String>> load(String query, Session session) {
        return new ArrayList<>();
    }

    public void save(List<List<String>> cells, String query, Session session) {
    }


}
