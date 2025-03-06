package org.example;

import com.lowagie.text.Document;

import java.io.FileOutputStream;

import com.lowagie.text.*;

import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;


public class reportForDifProtection {
    /**
     * Класс для создания отчета для дифзащиты.
     */
    public static void createReportForDifProtection(int rows, int columns, String properName) {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(properName));
            document.open();

            //создали таблицу нужного нам размера
            TableCreator testTable = new TableCreator(rows, columns);
            PdfPTable table = testTable.getTable();

            //объединяем необходимые нам ячейки в строке.
            testTable.mergeCellsInOneRow(0, 4, 5, "объединил ячейки в строке(04-05)", "0");

            //объединяем необходимые нам ячейки в строке.
            testTable.mergeCellsInOneColumn(0, 1, 2, "объединил ячейки в столбце(10-20)", "0");

            // Заполняем ячейки данными
            //первая строчка
            testTable.setCellContent(0, 0, "0", "0");
            testTable.setCellContent(0, 1, "0", "1");
            testTable.setCellContent(0, 2, "0", "2");
            testTable.setCellContent(0, 3, "0", "3");
            //вторая строчка
            testTable.setCellContent(1, 1, "1", "1");
            testTable.setCellContent(1, 2, "1", "2");
            testTable.setCellContent(1, 3, "1", "3");
            testTable.setCellContent(1, 4, "1", "4");
            testTable.setCellContent(1, 5, "1", "5");
            //третья строчка
            testTable.setCellContent(2, 1, "2", "1");
            testTable.setCellContent(2, 2, "2", "2");
            testTable.setCellContent(2, 3, "2", "3");
            testTable.setCellContent(2, 4, "2", "4");
            testTable.setCellContent(2, 5, "2", "5");

            document.add(table);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
            System.out.println("Файл создан. Файл называется - " + properName);
        }
    }
}