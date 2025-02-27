package org.example;

import com.aspose.pdf.Font;
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
    static String[] data = new String[]{"data[0]","data[1]", "data[2]", "data[3]", "data[4]"};
    static int rows = 3;
    static int columns = 6;


    // похуй
    static Document document = new Document();

    // Центральный метод, который создает отчет в пдф
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
    private static Paragraph getHeader(String[] data){
        Paragraph header = new Paragraph(data[0] + data[1]);
        return header;
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getScenarioInfo(String[] data){
        Paragraph scenarioInfo = new Paragraph(data[2] + data[3]);
        return scenarioInfo;
    }

    //Метод для создания таблицы
    private static PdfPTable getScenarioTable(String[] data, int rows, int columns) throws DocumentException, IOException {
        TableCreator infoTable = new TableCreator(rows, columns);
        PdfPTable table = infoTable.getTable();

        infoTable.mergeCellsInOneRow(0, 4, 5, "объединил ячейки в строке(04-05)", "0");

        //объединяем необходимые нам ячейки в строке.
        infoTable.mergeCellsInOneColumn(0, 1, 2, "объединил ячейки в столбце(10-20)", "0");

        // Заполняем ячейки данными
        //первая строчка
        infoTable.setCellContent(0, 0, data[0], "0");
        infoTable.setCellContent(0, 1, data[0], "1");
        infoTable.setCellContent(0, 2, "0", "2");
        infoTable.setCellContent(0, 3, "0", "3");
        //вторая строчка
        infoTable.setCellContent(1, 1, "1", "1");
        infoTable.setCellContent(1, 2, "1", "2");
        infoTable.setCellContent(1, 3, "1", "3");
        infoTable.setCellContent(1, 4, "1", "4");
        infoTable.setCellContent(1, 5, "1", "5");
        //третья строчка
        infoTable.setCellContent(2, 1, "2", "1");
        infoTable.setCellContent(2, 2, "2", "2");
        infoTable.setCellContent(2, 3, "2", "3");
        infoTable.setCellContent(2, 4, "2", "4");
        infoTable.setCellContent(2, 5, "2", "5");

        return table;
    }
    //Метод для вывода ошибки
    private static Paragraph getErrors(String[] data){
        Paragraph errors = new Paragraph("Аварийные сообщения: " + data[4]);
        return errors;
    }

    //Метод для выведения финальной черты
    private static Paragraph getDivided(){
        Paragraph Divided = new Paragraph("--------------------------------------------------------------------------------------");
        return Divided;
    }
}
