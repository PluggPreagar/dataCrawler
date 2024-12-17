package org.ppp;

import org.ppp.handler.ExcelHandler;

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


    }
}