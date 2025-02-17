package org.example;

//import com.aspose.pdf.Document;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.TextField;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("testTable.pdf"));
            document.open();

        int rows = 3;
        int columns = 6;

        BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);

        //создали таблицу нужного нам размера
        ContentCreator.TableCreator testTable = new ContentCreator.TableCreator();
        PdfPTable table = testTable.createTable(rows, columns, font);

        //объединяем необходимые нам ячейки в строке.
        testTable.mergeCellsInOneRow(0, 4,5,"объединил ячейки в строке(04-05)", font);

        //объединяем необходимые нам ячейки в строке.
        testTable.mergeCellsInOneColumn(0, 1,2,"объединил ячейки в столбце(10-20)", font);

        // Заполняем ячейки данными
            //первая строчка
        testTable.setCellContent(0,0,"00", font);
        testTable.setCellContent(0,1,"01", font);
        testTable.setCellContent(0,2,"02", font);
        testTable.setCellContent(0,3,"03", font);
            //вторая строчка
        testTable.setCellContent(1,1,"11", font);
        testTable.setCellContent(1,2,"12", font);
        testTable.setCellContent(1,3,"13", font);
        testTable.setCellContent(1,4,"14", font);
        testTable.setCellContent(1,5,"15", font);
            //третья строчка
        testTable.setCellContent(2,1,"21", font);
        testTable.setCellContent(2,2,"22", font);
        testTable.setCellContent(2,3,"23", font);
        testTable.setCellContent(2,4,"24", font);
        testTable.setCellContent(2,5,"25", font);

        document.add(table);

        } catch (DocumentException | IOException e){
            e.printStackTrace();
        } finally {
            document.close();
            System.out.println("Файл создан");
        }
    }
}

//    enum Scenarios {
//        Switcher, COMETRADE, DifferentialProtection, Protection1X, Protection3X, Hand
//    }

//        printProperReport(Scenarios.Switcher);
//        printProperReport(Scenarios.COMETRADE);
//        printProperReport(Scenarios.DifferentialProtection);
//        printProperReport(Scenarios.Protection1X);
//        printProperReport(Scenarios.Protection3X);
//        printProperReport(Scenarios.Hand);
//

//    public static void printProperReport(Scenarios scenario) {
//        switch (scenario) {
//            case Switcher -> reportForSwitcher();
//            case COMETRADE -> reportForCOMETRADE();
//            case DifferentialProtection -> reportForDifProtection();
//            case Protection1X -> reportForProtection1X();
//            case Protection3X -> reportForProtection3X();
//            case Hand -> reportForHand();
//        }














