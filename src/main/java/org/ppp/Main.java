package org.ppp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.ppp.handler.ExcelHandler;
import org.ppp.handler.HttpReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Session session = new Session();
        ExcelHandler excelReader = new ExcelHandler();
        List<List<String>> cells = new ArrayList<>();

        File file = new File("src/main/resources");
        for (File listFile : file.listFiles()) {

            if (listFile.getAbsolutePath().endsWith(".xlsx")){
                System.out.println("read xls-file: " + listFile.getAbsolutePath());
                List<List<String>> cellsCurrent = excelReader.load(listFile.getAbsolutePath(), session);
                cells.addAll(cellsCurrent);
            }

        }

        List<List<String>> load = new HttpReader().load("https://meine-rehabilitation.de/pr-backend/details?id_fach=679031002", session);
        System.out.printf(load.toString());
        String jsnString = load.toString();
        // convert to json

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(jsnString);
        // pretty print using gson
        String json = gson.toJson(jsonElement);
        System.out.println(json);
        // find email from json
        String email =  UtilJson.find(jsonElement, "email");
        System.out.println("email: " + email);

    }

}