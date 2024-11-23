package org.ppp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    void split_test(String expected, String line, String msg) {
        String delim = ",";
        String quote = "'";
        List<String> splits = Util.split(line, delim, quote);
        String join = String.join(" // ", splits);
        assertEquals(expected, join, msg);
    }
    
    @Test
    void split() {
        split_test("", "", "empty" );
        split_test("a", "a", "char" );
        split_test("aa", "aa", "char2" );
        //
        split_test("a // b", "a,b", "delim" );
        split_test("a // b // c", "a,b,c", "delim2" );
        //
        split_test("a,b", "'a,b'", "delim in quote start" );
        split_test("a // b,c", "a,'b,c'", "delim in quote last" );
        split_test("a // b,c // d", "a,'b,c',d", "delim in quote mid" );
    }

    @Test
    void splitBraces() {
        //
        split_test("a(,b)", "a(,b)", "quote by incomplete braces ..." );
        split_test("a // (b,c)", "a,(b,c)", "quote by incomplete braces last" );
        split_test("a // b(,c // d)", "a,'b(,c',d)", "ignore brace inside quote" );
    }


    @Test
    void quotesMatch() {
        Map<String, String> qm = Util.quotesMatch(Util.quotesPrio);
        assertEquals( qm.size()*2 , Util.quotesPrio.length());
        assertEquals( "\"", qm.get("\""));
        assertEquals( "'", qm.get("'"));
        assertEquals( "]", qm.get("["));
        assertEquals( "}", qm.get("{"));
    }
}