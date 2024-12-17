package org.ppp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class UtilJson {

    public static String find(JsonElement element, String key) {
        String val = null;
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(key)) {
                    val = entry.getValue().getAsString();
                } else {
                    val = find(entry.getValue(), key);
                }
                if (val != null) {
                    break;
                }
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement item : array) {
                val = find(item, key);
                if (val != null) {
                    break;
                }
            }
        }
        return val;
    }

}
