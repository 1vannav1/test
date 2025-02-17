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
    /**
     * Класс содержащий в себе другие подклассы для создания таблиц, текстовых полей и прочее.
     */
    public static class TableCreator {

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
                List<PdfPCell> rowCells = new ArrayList<>();
                for (int j = 0; j < columns; j++) {
                    PdfPCell cell = createCell("", font);
                    rowCells.add(cell);
                    table.addCell(cell);
                }
                cellGrid.add(rowCells);
            }
            return table;
        }

        // Метод для создания ячейки
        private PdfPCell createCell(String content, Font font) {
            PdfPCell cell = new PdfPCell(new Phrase(content, font));
            cell.setMinimumHeight(25);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            return cell;
        }

        // Метод для установки текста в ячейку
        public void setCellContent(int row, int column, String content, Font font) {
            if (row >= 0 && row < cellGrid.size() && column >= 0 && column < cellGrid.get(row).size()) {
                // Получаем ячейку из сетки
                PdfPCell cell = cellGrid.get(row).get(column);
                // Удаляем старую ячейку из таблицы
                table.getRow(row).getCells()[column] = null;
                // Создаем новую ячейку с обновленным содержимым
                PdfPCell newCell = createCell(content, font);
                // Заменяем ячейку в сетке
                cellGrid.get(row).set(column, newCell);
                // Добавляем новую ячейку в таблицу
                table.getRow(row).getCells()[column] = newCell;
            } else {
                throw new IllegalArgumentException("Недопустимые индексы строки или столбца");
            }
        }

        //метод для объединения двух ячеек в одной строке
        public void mergeCellsInOneRow(int row, int startColumn, int endColumn, String content, Font font) {
            // Проверяем, что startColumn и endColumn находятся в пределах таблицы
            if (startColumn >= endColumn || endColumn >= table.getNumberOfColumns()) {
                throw new IllegalArgumentException("Недопустимые индексы столбцов");
            }
            // Удаляем ячейки, которые будут объединены
            for (int i = startColumn; i <= endColumn; i++) {
                table.getRow(row).getCells()[i] = null;
            }
            // Создаем новую ячейку с объединением
            PdfPCell mergedCell = createCell(content, font);
            mergedCell.setColspan(endColumn - startColumn + 1);
            // Добавляем объединенную ячейку в таблицу
            table.getRow(row).getCells()[startColumn] = mergedCell;
        }

        //метод для объединения двух ячеек в одном стобце
        public void mergeCellsInOneColumn(int column, int startRow, int endRow, String content, Font font) {
            // Проверяем, что startRow и endRow находятся в пределах таблицы
            if (startRow >= endRow || endRow >= table.getRows().size()) {
                throw new IllegalArgumentException("Недопустимые индексы строк");
            }
            // Удаляем ячейки, которые будут объединены
            for (int i = startRow; i <= endRow; i++) {
                table.getRow(i).getCells()[column] = null;
            }
            // Создаем новую ячейку с объединением
            PdfPCell mergedCell = createCell(content, font);
            mergedCell.setRowspan(endRow - startRow + 1);
            // Добавляем объединенную ячейку в таблицу
            table.getRow(startRow).getCells()[column] = mergedCell;
        }
    }
    //public static cass
}




