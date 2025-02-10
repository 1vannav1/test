package org.example;

//import com.aspose.pdf.Document;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.TextField;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static Font actualFont;



//    private static void setFont() throws DocumentException, IOException {
//        BaseFont baseFont = BaseFont.createFont("ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        actualFont = new Font(baseFont, 12, Font.NORMAL);
//    }


    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("protocol_difzashchita.pdf"));
            document.open();

            //добавляем русский шрифт
            BaseFont baseFont = BaseFont.createFont("ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont,  18, Font.NORMAL);

            // Добавляем заголовок
            Paragraph title = new Paragraph("Протокол проверки дифференциальной защиты", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Добавляем дату и время
            Font dateFont = new Font(baseFont, 12, Font.NORMAL);
            Paragraph date = new Paragraph("Дата:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                    "       Время:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
                    dateFont);
            document.add(date);

            // Добавляем ФИО работника
            Paragraph worker = new Paragraph("ФИО работника: Иванов Иван Иванович", dateFont);
            document.add(worker);

            // Добавляем название оборудования
            Paragraph equipment = new Paragraph("Название оборудования: Т-16-У3", dateFont);
            document.add(equipment);

            // Добавляем форму проверки
            Paragraph checkForm = new Paragraph("Форма проверки: проверка дифференциальной защиты.", dateFont);
            document.add(checkForm);

            // Добавляем вид повреждения
            Paragraph damageType = new Paragraph("Вид повреждения: внешнее двухфазное КЗ на землю.", dateFont);
            document.add(damageType);

            // Добавляем таблицу 1
            PdfPTable table1 = new PdfPTable(2);
            table1.setWidthPercentage(50);
            table1.setSpacingBefore(10f);
            table1.setSpacingAfter(10f);

            addTableHeader(table1, new String[]{"Альфа", "1"}, dateFont);
            addTableRow(table1, new String[]{"Бета", "2"}, dateFont);
            addTableRow(table1, new String[]{"Гамма", "3"}, dateFont);
            addTableRow(table1, new String[]{"Штрих", "4"}, dateFont);

            document.add(table1);

            // Добавляем таблицу 2
            PdfPTable table2 = new PdfPTable(6);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(10f);
            table2.setSpacingAfter(10f);

            //addTableHeader(table2, new String[]{"I_A1", "100", "f_A1", "10", "Сработавшие контакты",""}, dateFont);
            //Создаю ячейки поочереди, так как только так их можно объединить
            PdfPCell cell00 = new PdfPCell(new Phrase("I_A1", dateFont));
            table2.addCell(cell00);

            PdfPCell cell01 = new PdfPCell(new Phrase("100", dateFont));
            table2.addCell(cell01);

            PdfPCell cell02 = new PdfPCell(new Phrase("f_A1", dateFont));
            table2.addCell(cell02);

            PdfPCell cell03 = new PdfPCell(new Phrase("10", dateFont));
            table2.addCell(cell03);

            PdfPCell mergedCell04 = new PdfPCell(new Phrase("Сработавшие контакты", dateFont));
            mergedCell04.setColspan(2);
            table2.addCell(mergedCell04);

            addTableRow(table2, new String[]{"I_B1", "100", "f_B1", "130", "1", "2"}, dateFont);
            addTableRow(table2, new String[]{"I_C1", "100", "f_C1", "280", "сработал", "нет"}, dateFont);

            //addTableRow(table2, new String[]{"I_A2", "50", "f_A2", "20", "Время срабатывания контакта, c",""}, dateFont);
            //Создаю ячейки поочереди, так как только так их можно объединить
            PdfPCell cell40 = new PdfPCell(new Phrase("I_A2", dateFont));
            table2.addCell(cell40);

            PdfPCell cell41 = new PdfPCell(new Phrase("50", dateFont));
            table2.addCell(cell41);

            PdfPCell cell42 = new PdfPCell(new Phrase("f_A2", dateFont));
            table2.addCell(cell42);

            PdfPCell cell43 = new PdfPCell(new Phrase("20", dateFont));
            table2.addCell(cell43);

            PdfPCell mergedCell44 = new PdfPCell(new Phrase("Время срабатывания контакта, c", dateFont));
            mergedCell44.setColspan(2);
            table2.addCell(mergedCell44);

            addTableRow(table2, new String[]{"I_B2", "50", "f_B2", "140", "1", "0.5"}, dateFont);
            addTableRow(table2, new String[]{"I_C2", "50", "f_C2", "290", "2", "-"}, dateFont);

            document.add(table2);
            document.add(getTestTable());

            // Добавляем информацию об ошибках
            Paragraph errors = new Paragraph("Зарегистрированные ошибки: нет", dateFont);
            document.add(errors);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
            System.out.println("Файл создан!");
        }
    }

    private static void addTableHeader(PdfPTable table, String[] headers, Font font) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private static void addTableRow(PdfPTable table, String[] rowData, Font font) {
        for (String data : rowData) {
            PdfPCell cell = new PdfPCell(new Phrase(data, font));
            table.addCell(cell);
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

    // Метод для создания ячейки с индексом
    private static PdfPCell createIndexedCell(String base, String index) {
        Chunk baseText = new Chunk(base, FontFactory.getFont(FontFactory.HELVETICA, 12));
        Chunk subscriptText = new Chunk(index, FontFactory.getFont(FontFactory.HELVETICA, 8));
        subscriptText.setTextRise(-3); // Смещение вниз для нижнего индекса

        Phrase phrase = new Phrase();
        phrase.add(baseText);
        phrase.add(subscriptText);

        PdfPCell cell = new PdfPCell(phrase);
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);


        return cell;
    }

    private static PdfPTable getTestTable() {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(50); // Ширина 50% страницы
        table.setSpacingBefore(10); // Отступ перед таблицей

        // Добавляем ячейки с индексами
        table.addCell(createIndexedCell("F", "A1"));
        table.addCell(createIndexedCell("1", "")); // Вторая колонка первой строки

        table.addCell(createIndexedCell("F", "A2"));
        table.addCell(createIndexedCell("0", "")); // Вторая колонка второй строки
        return table;
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