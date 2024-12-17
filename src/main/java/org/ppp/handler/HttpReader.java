package org.ppp.handler;

import org.ppp.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class HttpReader extends Handler {

    public HttpReader() {
        super(HttpReader.class);
    }

    @Override
    public List<List<String>> load(String query, Session session) {

        List<List<String>> load = super.load(query, session);
        String requestUrl = query;
        String jsonInputString = "{}";

        // read data from url into variable load
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            /*
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            */
            int code = connection.getResponseCode();
            if (code != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + code);
            } else {
                info("HTTP code: " + code);
            }

            handleResponse(connection, load);
        } catch (IOException e) {
            session.error(e);
        }


        return load;
    }

    void handleResponse(HttpURLConnection connection, List<List<String>> load) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                load.add(Collections.singletonList(responseLine));
            }
        }
    }


    @Override
    public void save(List<List<String>> cells, String query, Session session) {
        super.save(cells, query, session);
    }

}
