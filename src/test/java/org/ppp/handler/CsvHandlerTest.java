package org.ppp.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvHandlerTest {

    void tst( String line, String delim, String quote, String msg) {
        CsvHandler.Setting setting = new CsvHandler.Setting(line);
        assertEquals( delim, setting.delimiter , msg + "(delim)" );
        assertEquals( quote, setting.quotes , msg + "(quote)");
    }

    @Test
    void testBase(){
        tst("a,b", ",", "'", "simple");
        //
        tst("", ",", "'", "empty");
        tst("a", ",", "'", "single");
        tst("aa", ",", "'", "2char");
    }

    @Test
    void tstQuotes(){
        //
        tst("'a'", ",", "'", "quotes single");
        tst("\"a\"", ",", "\"", "quotes double");
        //
        tst("'aa'", ",", "'", "quotes single2");
        tst("\"aa\"", ",", "\"", "quotes double2");
        //
        tst("a,'b'", ",", "'", "quotes single on 2nd");
        tst("b,\"b\"", ",", "\"", "quotes double on 2nd");
        //
        tst("'a;a'", ",", "'", "quoted delim");
    }


}