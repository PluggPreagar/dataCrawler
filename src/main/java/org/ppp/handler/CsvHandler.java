package org.ppp.handler;

import org.ppp.Session;
import org.ppp.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvHandler extends Handler{

    public CsvHandler() {
        super( CsvHandler.class);
    }

    static class Setting {
        String delimiter = ",";
        String quotes = "'";

        public Setting(String firstLine) {
            if (null != firstLine && !firstLine.isEmpty()) {

                String quotes = firstLine.replaceAll("^.*?([\"']).*$|^.*$", "$1");
                if (quotes.length()>0) {
                    this.quotes= quotes.substring(0,1);
                    firstLine = firstLine.replaceAll("^" + quotes + ".*?" + quotes, ""); // remove leading quotes to find first delimiter
                } else {
                    quotes = "'"; // prefer java-style
                }
                delimiter = Util.optionalOnEmpty( firstLine.replaceAll("^.*?([,;\t]).*$|^.*$", "$1") , delimiter);

            }
        }
    }

    @Override
    public List<List<String>> load(String query, Session session) {

        List<List<String>> cells = super.load(query, session);
        URI filePath = null;
        try {
            filePath = new URI(query);
            try(BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
                String firstLine = br.readLine();
                Setting setting = new Setting( firstLine);
                String line = "";
                //
                while ( null != (line = br.readLine())) {
                    List<String> split = Util.split(line, setting.delimiter, setting.quotes);
                    cells.add(split);
                }
            }
        } catch (URISyntaxException e) {
            session.error(e);
        } catch (IOException e) {
            session.warn(e);
        }

        return cells ;
    }

    @Override
    public void save(List<List<String>> cells, String query, Session session) {

        super.save(cells, query, session);

    }

    public void save(List<List<String>> cells, String query, Session session, Setting setting) {

        String filePath = query;
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {

            for (List<String> row : cells) {
                for (String cell : row) {
                    cell = Util.quote(cell, setting.quotes, setting.delimiter);
                    bw.append( cell );
                    bw.append( setting.delimiter );
                }
                bw.append(System.lineSeparator());
            }

        } catch (IOException e) {
            session.error(e);
        }

        super.save(cells, query, session);
    }


}
