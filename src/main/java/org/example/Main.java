package org.example;

//import com.aspose.pdf.Document;
import com.aspose.pdf.Cells;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Table tableParametersOne;

    private static Font actualFont;


    private static void setFont() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont("ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        actualFont = new Font(baseFont, 12, Font.NORMAL);
    }
    public static void main(String[] args) throws DocumentException, IOException {
        setFont();

        TableParametersOne();
        String testString = "Дата:";

        writeToPdf(testString);

    }

    private static void TableParametersOne() throws DocumentException, IOException {
        //создание таблицы 1, параметров системы.
        tableParametersOne = new Table(2, 4);
        tableParametersOne.setPadding(2);
        tableParametersOne.setWidth(100);

        Cell[] cellsArray = new Cell[8];

        String textAlfa = "Альфа";

        cellsArray[0] = new Cell(new Phrase(textAlfa, actualFont));
        cellsArray[0].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[0]);


        cellsArray[1] = new Cell(new Phrase("1", actualFont));
        cellsArray[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[1]);

        String textBeta = "Бета" ;

        cellsArray[2] = new Cell(new Phrase(textBeta, actualFont));
        cellsArray[2].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[2]);

        cellsArray[3] = new Cell(new Phrase("2", actualFont));
        cellsArray[3].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[3]);

        String textGamma = "Gamma" ;

        cellsArray[4] = new Cell(new Phrase(textGamma, actualFont));
        cellsArray[4].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[4]);

        cellsArray[5] = new Cell(new Phrase("3", actualFont));
        cellsArray[5].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[5]);

        String textShtrih = "Штрих" ;

        cellsArray[6] = new Cell(new Phrase(textShtrih, actualFont));
        cellsArray[6].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[6]);

        cellsArray[7] = new Cell(new Phrase("4", actualFont));
        cellsArray[7].setHorizontalAlignment(Element.ALIGN_LEFT);
        tableParametersOne.addCell(cellsArray[7]);

                    //  добавление текста в таблицу
//        Cell[] cellsArray = new Cell[4];
//        for (int i = 0; i < cellsArray.length; i++) {
//
//            String textSample = "Sample text" + i;
//
//            cellsArray[i] = new Cell(new Phrase(
//                    textSample, FontFactory.
//                    getFont(FontFactory.HELVETICA, 12)));
//
//            cellsArray[i].setHorizontalAlignment(Element.ALIGN_LEFT);
//            tableParameters.addCell(cellsArray[i]);
        }


    private static void writeToPdf(String text) {
        try {
            Document document = new Document();

            //создание файла
            PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
            document.open();



            //добавление заглавия ("Дата: 'dataText'    Время: 'timeText'")
            document.add(new Paragraph(text, actualFont));

            document.add(tableParametersOne);

            //закрываем файл, пишем об удачном создании
            document.close();
            System.out.println("Файл создан!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    enum Scenarios {
        Switcher, COMETRADE, DifferentialProtection, Protection1X, Protection3X, Hand
    }

    public void printProperReport(Scenarios scenario) {
        switch (scenario) {
            case Switcher -> reportForSwitcher();
            case COMETRADE -> reportForCOMETRADE();
            case DifferentialProtection -> reportForDifProtection();
            case Protection1X -> reportForProtection1X();
            case Protection3X -> reportForProtection3X();
            case Hand -> reportForHand();
        }
    }

    private void reportForDifProtection() {


    }

    private void reportForSwitcher() {

    }

    private void reportForCOMETRADE() {

    }

    private void reportForProtection1X() {

    }

    private void reportForProtection3X() {

    }

    private void reportForHand() {

    }
}