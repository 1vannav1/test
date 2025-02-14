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
        int columns = 3;

        BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);

        ContentCreator.TableCreator tableCreator = new ContentCreator.TableCreator();
        PdfPTable table = tableCreator.createTable(rows, columns, font);

            // Заполняем ячейки данными
        tableCreator.addElement(0, 0, "1", font);
        tableCreator.addElement(0, 1, "2", font);
        tableCreator.addElement(0, 2, "3", font);

        tableCreator.addElement(1, 0, "1", font);
        tableCreator.addElement(1, 1, "2", font);
        tableCreator.addElement(1, 2, "3", font);

        tableCreator.addElement(2, 0, "1", font);
        tableCreator.addElement(2, 1, "2", font);
        tableCreator.addElement(2, 2, "3", font);

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














