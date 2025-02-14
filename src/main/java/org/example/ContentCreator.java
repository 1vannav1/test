package org.example;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;

import static org.example.settings.createFont;

public class ContentCreator {

    public static class TableCreator {

       // Font font = new settings(createFont());

        private PdfPTable table;
        private List<List<PdfPCell>> cellGrid; // Сетка ячеек для доступа по индексам

        // Основной метод для создания таблицы
        public PdfPTable createTable(int rows, int columns, Font font) {
            table = new PdfPTable(columns);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Инициализация сетки ячеек
            cellGrid = new ArrayList<>();

            // Создание строк и ячеек
            for (int i = 0; i < rows; i++) {
                List<PdfPCell> rowCells = createRow(columns, font);
                cellGrid.add(rowCells);
                for (PdfPCell cell : rowCells) {
                    table.addCell(cell);
                }
            }

            return table;
        }

        // Метод для создания строки
        private List<PdfPCell> createRow(int columns, Font font) {
            List<PdfPCell> rowCells = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                PdfPCell cell = createCell("", font);
                rowCells.add(cell);
            }
            return rowCells;
        }

        // Метод для создания ячейки
        private PdfPCell createCell(String content, Font font) {
            PdfPCell cell = new PdfPCell(new Phrase(content, font));
            cell.setMinimumHeight(25);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            return cell;
        }

        //метод для перехода в нужную нам ячейку
        private PdfPCell getCell(int row, int column) {
            if (row >= 0 && row < cellGrid.size() && column >= 0 && column < cellGrid.get(row).size()) {
                return cellGrid.get(row).get(column);
            }
            throw new IllegalArgumentException("Недопустимые индексы строки или столбца");
        }

        public void addElement(int row, int column, String content, Font font){
            PdfPCell cell = getCell(row, column);
            cell.setPhrase(new Phrase(content, font));
        }

        //метод для объединения двух ячеек в одной строке
        private static void mergeCellsInOneRow(int row, int column1, int column2) {
        }

        //метод для объединения двух ячеек в одном стобце
        private static void mergeCellsInOneColumn(int row1, int row2, int column) {
        }
    }
}



