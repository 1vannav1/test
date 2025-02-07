package org.example;

//import com.aspose.pdf.Document;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.TextField;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws DocumentException, IOException {
        //Пробный перменные для вывода данных
        String time = "50";
        String FIO = "Иван Иванович Иванов";
        //Пробный перменные для вывода данных
        String fontPath = "C:\\Users\\ing8\\IdeaProjects\\test\\target\\ofont.ru_Myriad Pro.ttf"; // Укажите правильный путь к TTF файлу
        String outputPath = "output.pdf";

        Document document = new Document();
        PdfWriter writer = null;

        try {
            //создание файла
            writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            //Запись начального текста
            writeInitialText(writer, fontPath);
            //tableParametersOne();
            //закрываем файл, пишем об удачном создании
        }
        catch (DocumentException | IOException e) {
                e.printStackTrace();
        }

        finally {
            document.close();
            System.out.println("Файл создан!");
        }
    }


    private static void writeInitialText(PdfWriter writer, String fontPath) throws IOException  {
        try {
            // Задаем шрифт, который поддерживает кириллицу
            BaseFont bf = BaseFont.createFont(fontPath,
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            // Создаем PdfContentByte для рисования на документе
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            cb.setFontAndSize(bf, 12);

            // Начальные координаты для текста
            float x = 100;
            float y = 800; // Начальная позиция по оси Y

            // Печатаем несколько строк текста
            String[] lines = {
                    "Дата:          Время:" ,
                    "ФИО исполнителя:" ,
                    "Название объекта: Т-16-132632лрилытлтва",
            };

            for (String line : lines) {
                cb.setTextMatrix(x, y);
                cb.showText(line);
                y -= 15; // Уменьшаем y, чтобы перейти к следующей строке (15 - высота строки)
            }

            cb.endText();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
//ИЗМЕНИТЬ метод для создания таблицы
//    private static void tableParametersOne() throws DocumentException, IOException {
//        //создание таблицы 1, параметров системы.
//        tableParametersOne = new Table(2, 4);
//        tableParametersOne.setPadding(2);
//        tableParametersOne.setWidth(100);
//
//        Cell[] cellsArray = new Cell[8];
//
//        String textAlfa = "Альфа";
//
//        cellsArray[0] = new Cell(new Phrase(textAlfa, actualFont));
//        cellsArray[0].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[0]);
//
//
//        cellsArray[1] = new Cell(new Phrase("1", actualFont));
//        cellsArray[1].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[1]);
//
//        String textBeta = "Бета" ;
//
//        cellsArray[2] = new Cell(new Phrase(textBeta, actualFont));
//        cellsArray[2].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[2]);
//
//        cellsArray[3] = new Cell(new Phrase("2", actualFont));
//        cellsArray[3].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[3]);
//
//        String textGamma = "Gamma" ;
//
//        cellsArray[4] = new Cell(new Phrase(textGamma, actualFont));
//        cellsArray[4].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[4]);
//
//        cellsArray[5] = new Cell(new Phrase("3", actualFont));
//        cellsArray[5].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[5]);
//
//        String textShtrih = "Штрих" ;
//
//        cellsArray[6] = new Cell(new Phrase(textShtrih, actualFont));
//        cellsArray[6].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[6]);
//
//        cellsArray[7] = new Cell(new Phrase("4", actualFont));
//        cellsArray[7].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[7]);
//        }


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