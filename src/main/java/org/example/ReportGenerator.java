package org.example;

import com.aspose.pdf.IDocument;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.lang.model.util.Elements;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportGenerator {
    /** Класс по созданию ПДФ отчета. В данном классе лежат основные методы для генерации элементов
     * пдф файла. В дальнейшем этот объект будет использоваться для создания отчета целиком.
     */




    //нужное для методов работы
    static String fileName = "fileName.pdf";
    static String[] data = new String[]{"ИМЯ РЕЖИМА","01.01.2025", "12.00.00", "data[3]", "data[4]"};
    static int rows = 7;
    static int columns = 6;

    String[][] getBuferData = new String[][]{{fileName},data,{},{}};

    static Font textFont;
    static Settings settings;

    static {
        try {
            settings = new Settings();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    // похуй
    static Document document = new Document();


    // Центральный метод, который собирает элементы для отчета в пдф
    public static void generateReport() throws DocumentException, IOException {

        Element[] reportElements = new Element[5];  //создание массива элементов для генератора пдф

        reportElements[0] = getHeader(data);
        reportElements[1] = getScenarioInfo(data);
        reportElements[2] = getScenarioTable(data, rows, columns);
        reportElements[3] = getErrors(data);
        reportElements[4] = getDivided();

        createPDF(reportElements, fileName);
    }

    //Метод для создания пдф отчета
    private static void createPDF(Element[] reportElements, String fileName) throws DocumentException {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
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
            System.out.println("Файл создался в reportGenerator. Имя файла - " + fileName);
        }
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getHeader(String[] data) throws DocumentException, IOException {
        //настройки шрифта заголовка
        int v = 18;
        textFont = settings.getFont(v);

        Paragraph paragraph = new Paragraph("ПРОТОКОЛ «" + data[0] + "»", textFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getScenarioInfo(String[] data){
        //настройки шрифта времени и даты
        int v = 14;
        textFont = settings.getFont(v);

        // формируем строку
        String strScenario = """
                Дата: %s Время: %s
                ФИО работника: 
                Название объекта: 
                Схема соединения трансформатора: 
                Вид повреждения:
                Таблица 1. Параметры режима  
                """.formatted(data[1],data[2]);

        Paragraph paragraph = new Paragraph(strScenario, textFont);
        return paragraph;
    }

    //Метод для создания таблицы
    private static PdfPTable getScenarioTable(String[] data, int rows, int columns) throws DocumentException, IOException {

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
        infoTable.setCellContent(1, 1, "101", "");
        infoTable.setCellContent(1, 2, "\u03C6", "A1");
        infoTable.setCellContent(1, 3, "10", "");
        //третья строчка
        infoTable.setCellContent(2, 0, "I", "B1");
        infoTable.setCellContent(2, 1, "102", "");
        infoTable.setCellContent(2, 2, "\u03C6", "B1");
        infoTable.setCellContent(2, 3, "130", "");
        infoTable.mergeCellsInOneColumn(4, 1,3,"1","");
        infoTable.mergeCellsInOneColumn(5, 1,3,"1","");
        //четвертая строчка
        infoTable.setCellContent(3, 0, "I", "c1");
        infoTable.setCellContent(3, 1, "103", "");
        infoTable.setCellContent(3, 2, "\u03C6", "С1");
        infoTable.setCellContent(3, 3, "280", "");
        //пятая строчка
        infoTable.setCellContent(4, 0, "I", "A2");
        infoTable.setCellContent(4, 1, "51", "");
        infoTable.setCellContent(4, 2, "\u03C6", "A2");
        infoTable.setCellContent(4, 3, "20", "");
        //шестая строчка
        infoTable.setCellContent(5, 0, "I", "B2");
        infoTable.setCellContent(5, 1, "52", "");
        infoTable.setCellContent(5, 2, "\u03C6", "B2");
        infoTable.setCellContent(5, 3, "140", "");
        infoTable.mergeCellsInOneColumn(4, 4,6,"да","");
        infoTable.mergeCellsInOneColumn(5, 4,6,"нет","");
        //седьмая строчка
        infoTable.setCellContent(6, 0, "I", "С2");
        infoTable.setCellContent(6, 1, "53", "");
        infoTable.setCellContent(6, 2, "\u03C6", "С2");
        infoTable.setCellContent(6, 3, "290", "");

        return table;
    }
    //Метод для вывода ошибки
    private static Paragraph getErrors(String[] data){
        int v = 14;
        textFont = settings.getFont(v);

        return new Paragraph("Аварийные сообщения: " + data[4], textFont);
    }

    //Метод для выведения финальной черты
    private static Paragraph getDivided(){
        return new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
    }
}
