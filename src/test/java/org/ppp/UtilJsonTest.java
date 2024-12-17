package org.ppp;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilJsonTest {

    @Test
    void testFindEmail() {
        String json = "{ \"user\": { \"name\": \"John\", \"contact\": { \"email\": \"john.doe@example.com\" } } }";
        JsonElement jsonElement = JsonParser.parseString(json);
        String email = UtilJson.find(jsonElement, "email");
        assertEquals("john.doe@example.com", email);
    }

    @Test
    void testFindNonExistentKey() {
        String json = "{ \"user\": { \"name\": \"John\", \"contact\": { \"email\": \"john.doe@example.com\" } } }";
        JsonElement jsonElement = JsonParser.parseString(json);
        String phone = UtilJson.find(jsonElement, "phone");
        assertNull(phone);
    }

    @Test
    void testFindInArray() {
        String json = "{ \"users\": [{ \"name\": \"John\", \"email\": \"john.doe@example.com\" }, { \"name\": \"Jane\", \"email\": \"jane.doe@example.com\" }] }";
        JsonElement jsonElement = JsonParser.parseString(json);
        String email = UtilJson.find(jsonElement, "email");
        assertEquals("john.doe@example.com", email);
    }

    @Test
    void testFindNestedKey() {
        String json = "{ \"a\": { \"b\": { \"c\": \"value\" } } }";
        JsonElement jsonElement = JsonParser.parseString(json);
        String value = UtilJson.find(jsonElement, "c");
        assertEquals("value", value);
    }


    @Test
    void testFindNestedKeyInArray() {
        String json = "[[{\"messages\":[],\"meta\":{\"version\":\"1.0.1.0\",\"responseTime\":5},\"data\":{\"einrichtung\":{\"name\":\"Klinik am Korso\"},\"fachabteilung\":{\"idFach\":\"679031002\",\"fasc\":\"3100\",\"plz\":\"32545\",\"ort\":\"Bad Oeynhausen\",\"strasse\":\"Ostkorso\",\"hausnummer\":\"4\",\"lat\":52.20162895,\"lon\":8.79924688683315,\"telefon\":\"05731 1811114\",\"email\":\"aufnahme@klinik-am-korso.de\",\"web\":\"https://www.klinik-am-korso.de\",\"durchfuehrung\":\"zdb.ergebnis.duchfuehrung.stationaer\",\"isAhb\":false,\"isGkv\":false,\"qualitaet\":78,\"qualitaetMw\":78,\"wartezeit\":{\"gruppe\":2,\"tokenLong\":\"app.wartezeit.ampel.moderat.long\",\"tokenShort\":\"app.wartezeit.ampel.moderat.short\"}},\"bewertung\":[{\"sort\":10,\"label\":\"page.detail.qualitaet.besserung.label\",\"qs\":62,\"neutralerWert\":true,\"mwQs\":62,\"info\":\"page.detail.qualitaet.besserung.info\"},{\"sort\":20,\"label\":\"page.detail.qualitaet.zufriedenheit.label\",\"qs\":68,\"neutralerWert\":true,\"mwQs\":68,\"info\":\"page.detail.qualitaet.zufriedenheit.info\"},{\"sort\":30,\"label\":\"page.detail.qualitaet.reha.label\",\"qs\":95,\"neutralerWert\":true,\"mwQs\":95,\"info\":\"page.detail.qualitaet.reha.info\"},{\"sort\":40,\"label\":\"page.detail.qualitaet.klassifikation.label\",\"qs\":98,\"neutralerWert\":false,\"mwQs\":92,\"info\":\"page.detail.qualitaet.klassifikation.info\"},{\"sort\":50,\"label\":\"page.detail.qualitaet.peerreview.label\",\"qs\":68,\"neutralerWert\":false,\"mwQs\":74,\"info\":\"page.detail.qualitaet.peerreview.info\"}],\"sondermerkmale\":[{\"label\":\"zdb.details.sonderanforderungen.1\",\"childs\":[\"zdb.details.sonderanforderungen.103\",\"zdb.details.sonderanforderungen.104\",\"zdb.details.sonderanforderungen.102\"]},{\"label\":\"zdb.details.sonderanforderungen.4\",\"childs\":[\"zdb.details.sonderanforderungen.404\"]}],\"weitereFachabteilungen\":[]}}]]";
        JsonElement jsonElement = JsonParser.parseString(json);
        assertEquals("1.0.1.0", UtilJson.find(jsonElement, "version"));
        assertEquals("aufnahme@klinik-am-korso.de", UtilJson.find(jsonElement, "email"));
    }

}