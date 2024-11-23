package org.ppp.handler;

import org.apache.poi.ss.usermodel.*;
import org.ppp.Session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandler extends Handler {


    @Override
    public List<List<String>> load(String filePath, Session session) {
        List<List<String>> cells = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
            for (Row row : sheet) {
                List<String> rowCells = new ArrayList<>();
                rowCells.add( filePath.replaceAll(".*[\\\\/]",""));
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowCells.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowCells.add("" + cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowCells.add("" + cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            rowCells.add(cell.getCellFormula());
                            break;
                        default:
                            rowCells.add("");
                    }
                }
                cells.add( rowCells);
                System.out.println( String.join(";", rowCells) );
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cells;
    }

}
