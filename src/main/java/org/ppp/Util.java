package org.ppp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Util {

    static String quotesPrio = "\"'({[]})'\""; // including closing elements
    static Map<String, String> quotesMatch = quotesMatch( quotesPrio);

    static final Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");


    static public Map<String , String> quotesMatch(String quotesPrio){
        Map<String, String> quotesMatch = new HashMap<>();
        for (int i = 0; i < quotesPrio.length()/2 ; i++) {
            quotesMatch.put( quotesPrio.substring(i,i+1), quotesPrio.substring( quotesPrio.length() - i - 1,quotesPrio.length() - i ) );
        }
        return quotesMatch;
    }

    static String escapeSpecialRegexChars(String str) {
        return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
    }




    static public List<String> split(String line, String delimiter, String quote) {
        List<String> strings = new ArrayList<>(); // allow escaping with "\" or double
        strings.add("");
        String patternString = escapeSpecialRegexChars(delimiter + quote + quotesPrio);
        String[] parts = line.split("(?=[" + patternString + "])"); // use a dumb split first ...
        List<String> quotes = new ArrayList<>();
        boolean ignoreSplit = false;
        for (String part : parts) {
            // part may start with quoting or delimiter ...
            if (ignoreSplit) {
                strings.set(strings.size() - 1, strings.getLast() + part); // append to last
            } else if (part.isEmpty()) {
                // skipp
            } else {

                if (part.startsWith(quote)) {
                    part = part.substring(1);
                    if (quotes.isEmpty()) { // opening quote
                        quotes.add(quote);
                    } else {                // closing quote
                        quotes.removeLast();
                    }
                } else {

                    String str_ = part.substring(0, 1);
                    // assume quote is in quotesPrio
                    if (quotesPrio.contains(str_)) { // potential brace-element
                        if (!quotes.isEmpty() && quotes.getLast().equals(quote)) {
                            // skipp within quoting ... wait for master-quote to end
                        } else {
                            if (!quotes.isEmpty() && quotesMatch.get(quotes.getLast()).equals(str_)) {
                                quotes.removeLast();
                            } else if (!quotes.isEmpty() && quotesPrio.indexOf(quotes.getLast()) < quotesPrio.indexOf(str_) ) {
                                // skipp if brace with higher prio still unmatched
                            } else {
                                quotes.add(str_);
                            }
                        }
                    }

                }

                if (quotes.isEmpty() && part.startsWith(delimiter)) { // valid delimiter
                    part = part.substring(1);
                    strings.add( part ); // append new string
                } else {
                    strings.set( strings.size() - 1, strings.getLast() + part ); // append to last string
                }

            }
            //
            ignoreSplit = part.endsWith("\\");
        }

        return strings;
    }

    static public String optionalOnEmpty(String val, String fallback) {
        return null != val && !val.isEmpty() ? val : fallback;
    }

    static public String optional(String val, String fallback) {
        return null != val ? val : fallback;
    }

    /*
    public <T>  Object<T> optional(Object<T> val, Object<T> fallback) {
        return null != val ? val : fallback;
    }

     */
}
