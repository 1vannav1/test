package org.example;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportGenerator {
    /** Класс по созданию ПДФ отчета. В данном классе лежат основные методы для генерации элементов
     * пдф файла. В дальнейшем этот объект будет использоваться для создания отчета целиком.
     */

    //нужное для методов работы
    static int rows = 7;
    static int columns = 6;

    static String[] fileName = new String[]{"fileName.pdf"};
    static String[] element0 = new String[]{"Название режима"};
    static String[] element1 = new String[]{"01.01.2025", "12.00.00", "Т-16-У3", "Иванов Иван Иванович", "D-0/y-1", "ABG"};
    static String[] element2 = new String[]{"101", "10", "102", "130", "103", "250", "51", "20", "52", "160", "53", "280","да", "нет"};
    static String[] element3 = new String[]{"Аварий нет"};

    static String[][] bufferData = new String[][]{}; //{fileName, element0, element1, element2, element3};

    static Font textFont;
    static Settings settings;

    static {
        try {
            settings = new Settings();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Document document = new Document();

    // Центральный метод, который собирает элементы для отчета в пдф
    public static void generateReport(String[][] abdfba) throws DocumentException, IOException {

        bufferData = abdfba;

        Element[] reportElements = new Element[5];  //создание массива элементов для генератора пдф

        reportElements[0] = getHeader(bufferData);
        reportElements[1] = getScenarioInfo(bufferData);
        reportElements[2] = getScenarioTable(bufferData, rows, columns);
        reportElements[3] = getErrors(bufferData);
        reportElements[4] = getDivided();

        createPDF(reportElements, bufferData);
    }

    //Метод для создания пдф отчета
    private static void createPDF(Element[] reportElements, String[][] bufferData) throws DocumentException {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(bufferData[0][0]));
            document.open();
            document.add(reportElements[0]);
            document.add(reportElements[1]);
            document.add(reportElements[2]);
            document.add(reportElements[3]);
            document.add(reportElements[4]);

        } catch (FileNotFoundException | DocumentException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
            System.out.println("Файл создался в reportGenerator. Имя файла - " + bufferData[0][0]);
        }
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getHeader(String[][] bufferData) throws DocumentException, IOException {
        //настройки шрифта заголовка
        int v = 18;
        textFont = settings.getFont(v);

        Paragraph paragraph = new Paragraph("ПРОТОКОЛ «" + bufferData[1][0] + "»", textFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getScenarioInfo(String[][] bufferData){
        //настройки шрифта времени и даты
        int v = 14;
        textFont = settings.getFont(v);

        // формируем строку
        String strScenario = """
                Дата: %s Время: %s
                ФИО работника: %s
                Название объекта: %s
                Схема соединения трансформатора: %s
                Вид повреждения: %s
                Таблица 1. Параметры режима
                """.formatted(bufferData[2][0], bufferData[2][1], bufferData[2][2], bufferData[2][3], bufferData[2][4], bufferData[2][5]);

        Paragraph paragraph = new Paragraph(strScenario, textFont);
        return paragraph;
    }

    //Метод для создания таблицы
    private static PdfPTable getScenarioTable(String[][] bufferData, int rows, int columns) throws DocumentException, IOException {

        TableCreator infoTable = new TableCreator(rows, columns);
        PdfPTable table = infoTable.getTable();
                               //Заполняем ячейки данными
        //первая строчка
        infoTable.setCellContent(0, 0, "Параметры", "");
        infoTable.setCellContent(0, 1, "Значения, А", "");
        infoTable.setCellContent(0, 2, "Параметры", "");
        infoTable.setCellContent(0, 3, "Значения, град", "");
        infoTable.mergeCellsInOneRow(0, 4, 5, "Сработавшие контакты", "");
        //вторая строчка
        infoTable.setCellContent(1, 0, "I", "A1");
        infoTable.setCellContent(1, 1, bufferData[3][0], "");
        infoTable.setCellContent(1, 2, "\u03C6", "A1");
        infoTable.setCellContent(1, 3, bufferData[3][1], "");
        //третья строчка
        infoTable.setCellContent(2, 0, "I", "B1");
        infoTable.setCellContent(2, 1, bufferData[3][2], "");
        infoTable.setCellContent(2, 2, "\u03C6", "B1");
        infoTable.setCellContent(2, 3, bufferData[3][3], "");
        infoTable.mergeCellsInOneColumn(4, 1,3,"1","");
        infoTable.mergeCellsInOneColumn(5, 1,3,"1","");
        //четвертая строчка
        infoTable.setCellContent(3, 0, "I", "c1");
        infoTable.setCellContent(3, 1, bufferData[3][4], "");
        infoTable.setCellContent(3, 2, "\u03C6", "С1");
        infoTable.setCellContent(3, 3, bufferData[3][5], "");
        //пятая строчка
        infoTable.setCellContent(4, 0, "I", "A2");
        infoTable.setCellContent(4, 1, bufferData[3][6], "");
        infoTable.setCellContent(4, 2, "\u03C6", "A2");
        infoTable.setCellContent(4, 3, bufferData[3][7], "");
        //шестая строчка
        infoTable.setCellContent(5, 0, "I", "B2");
        infoTable.setCellContent(5, 1, bufferData[3][8], "");
        infoTable.setCellContent(5, 2, "\u03C6", "B2");
        infoTable.setCellContent(5, 3, bufferData[3][9], "");
        infoTable.mergeCellsInOneColumn(4, 4,6,bufferData[3][12],"");
        infoTable.mergeCellsInOneColumn(5, 4,6,bufferData[3][13],"");
        //седьмая строчка
        infoTable.setCellContent(6, 0, "I", "С2");
        infoTable.setCellContent(6, 1, bufferData[3][10], "");
        infoTable.setCellContent(6, 2, "\u03C6", "С2");
        infoTable.setCellContent(6, 3, bufferData[3][11], "");

        return table;
    }
    //Метод для вывода ошибки
    private static Paragraph getErrors(String[][] bufferData){
        int v = 14;
        textFont = settings.getFont(v);

        return new Paragraph("Аварийные сообщения: " + bufferData[4][0], textFont);
    }

    //Метод для выведения финальной черты
    private static Paragraph getDivided(){
        return new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
    }
}
