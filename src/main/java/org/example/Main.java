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

            //запись построчно
            String[] rowData0Table1 = {"Альфа","<A>", "1",""};
            addTableRow(table1, rowData0Table1, dateFont);
            String[] rowData1Table1 = {"Бета","<Б>", "2",""};
            addTableRow(table1, rowData1Table1, dateFont);
            String[] rowData2Table1 = {"Гамма","<Г>", "3",""};
            addTableRow(table1, rowData2Table1, dateFont);
            String[] rowData3Table1 = {"Штрих", "</>", "4",""};
            addTableRow(table1, rowData3Table1, dateFont);

            document.add(table1);

            // Добавляем таблицу 2
            PdfPTable table2 = new PdfPTable(6);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(10f);
            table2.setSpacingAfter(10f);

            //Создаю ячейки поочереди, так как только так их можно объединить
            table2.addCell(createIndexedCell("I", "A1", dateFont));
            table2.addCell(createIndexedCell("100", "", dateFont));
            table2.addCell(createIndexedCell("f", "A1", dateFont));
            table2.addCell(createIndexedCell("10", "", dateFont));
            PdfPCell mergedCell04 = new PdfPCell(new Phrase("Сработавшие контакты", dateFont));
            mergedCell04.setColspan(2);
            table2.addCell(mergedCell04);

            String[] rowData1Table2 = {"I","B1", "100","", "f", "B1", "130","", "1","", "2",""};
            addTableRow(table2, rowData1Table2, dateFont);
            String[] rowData2Table2 = {"I", "C1", "100","", "f", "C1", "280", "", "сработал", "", "нет",""};
            addTableRow(table2, rowData2Table2, dateFont);

            //Создаю ячейки поочереди, так как только так их можно объединить
            table2.addCell(createIndexedCell("I", "A2", dateFont));
            table2.addCell(createIndexedCell("50", "", dateFont));
            table2.addCell(createIndexedCell("f", "A2", dateFont));
            table2.addCell(createIndexedCell("20", "", dateFont));
            PdfPCell mergedCell44 = new PdfPCell(new Phrase("Время срабатывания контакта, c", dateFont));
            mergedCell44.setColspan(2);
            table2.addCell(mergedCell44);

            String[] rowData5Table2 = {"I","B2", "50","", "f","B2", "140","", "1","", "0.5",""};
            addTableRow(table2, rowData5Table2, dateFont);
            String[] rowData6Table2 = {"I","C2", "50","", "f", "C2", "290","", "2","", "-",""};
            addTableRow(table2, rowData6Table2, dateFont);

            document.add(table2);
            document.add(getTestTable(dateFont));

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
        for (int i = 0; i < rowData.length; i += 2) {
            String baseText = rowData[i]; //основной текст
            String indexText = rowData[i+1]; //индекс текста


            //Если индекс пустой
            PdfPCell cell;
            if (indexText.isEmpty()) {
                cell = new PdfPCell(new Phrase(baseText, font));
            } else {
                cell = createIndexedCell(baseText, indexText, font);
            }

            //настройка ячейки
            cell.setMinimumHeight(25);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_LEFT);

            table.addCell(cell);
        }
    }

    // Метод для создания ячейки с индексом
    private static PdfPCell createIndexedCell(String base, String index, Font font) {
        Chunk baseText = new Chunk(base, font);
        Chunk subscriptText = new Chunk(index, font);
        subscriptText.setTextRise(-3); // Смещение вниз для нижнего индекса

        Phrase phrase = new Phrase();
        phrase.add(baseText);
        phrase.add(subscriptText);

        PdfPCell cell = new PdfPCell(phrase);
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);

        return cell;
    }

    private static PdfPTable getTestTable(Font font) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(50); // Ширина 50% страницы
        table.setSpacingBefore(10); // Отступ перед таблицей

        // Добавляем ячейки с индексами
        table.addCell(createIndexedCell("I", "A1", font));
        table.addCell(createIndexedCell("1", "", font)); // Вторая колонка первой строки

        table.addCell(createIndexedCell("I", "A2", font));
        table.addCell(createIndexedCell("0", "", font)); // Вторая колонка второй строки
        return table;
    }



//    private  static  PdfPTable createTable1(Font font) {
//        //формируем ячейки
//        PdfPCell cell00 = new PdfPCell(new Phrase("Альфа", font));
//        PdfPCell cell01 = new PdfPCell(new Phrase("1", font));
//        PdfPCell cell10 = new PdfPCell(new Phrase("Бета", font));
//        PdfPCell cell11 = new PdfPCell(new Phrase("2", font));
//        PdfPCell cell20 = new PdfPCell(new Phrase("Гамма", font));
//        PdfPCell cell21 = new PdfPCell(new Phrase("3", font));
//        PdfPCell cell30 = new PdfPCell(new Phrase("Штрих", font));
//        PdfPCell cell31 = new PdfPCell(new Phrase("4", font));
//
//        //Формируем строки
//        List<PdfPRow> rows = new ArrayList<>();
//        rows.add(new PdfPRow(new PdfPCell[]{cell00, cell01}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell10, cell11}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell20, cell21}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell30, cell31}));
//
//        //Создаем таблицу и добавляем строки
//        PdfPTable table = new PdfPTable(2);
//        table.setWidthPercentage(50);
//        table.setSpacingBefore(10f);
//        table.setSpacingAfter(10f);
//
//        for (PdfPRow row : rows) {
//            for (PdfPCell cell : row.getCells()) {
//                table.addCell(cell);
//            }
//        }
//
//    return  table;
//    }
//
//
//    private  static  PdfPTable createTable2(Font font) {
//       //первая строчка
//        PdfPCell cell00 = new PdfPCell(new Phrase("I_A1", font));
//        PdfPCell cell01 = new PdfPCell(new Phrase("100", font));
//        PdfPCell cell02 = new PdfPCell(new Phrase("f_A1", font));
//        PdfPCell cell03 = new PdfPCell(new Phrase("10", font));
//        PdfPCell cell04 = new PdfPCell(new Phrase("Сработавшие контакты", font));
//        cell04.setColspan(2); //объединение ячеек
//
//        //вторая строчка
//        PdfPCell cell10 = new PdfPCell(new Phrase("I_B1", font));
//        PdfPCell cell11 = new PdfPCell(new Phrase("100", font));
//        PdfPCell cell12 = new PdfPCell(new Phrase("f_B1", font));
//        PdfPCell cell13 = new PdfPCell(new Phrase("130", font));
//        PdfPCell cell14 = new PdfPCell(new Phrase("1", font));
//        PdfPCell cell15 = new PdfPCell(new Phrase("2", font));
//
//        //третья строчка
//        PdfPCell cell20 = new PdfPCell(new Phrase("I_C1", font));
//        PdfPCell cell21 = new PdfPCell(new Phrase("100", font));
//        PdfPCell cell22 = new PdfPCell(new Phrase("f_С1", font));
//        PdfPCell cell23 = new PdfPCell(new Phrase("280", font));
//        PdfPCell cell24 = new PdfPCell(new Phrase("сработал", font));
//        PdfPCell cell25 = new PdfPCell(new Phrase("нет", font));
//
//        //четвертая строчка
//        PdfPCell cell30 = new PdfPCell(new Phrase("I_А2", font));
//        PdfPCell cell31 = new PdfPCell(new Phrase("50", font));
//        PdfPCell cell32 = new PdfPCell(new Phrase("f_А2", font));
//        PdfPCell cell33 = new PdfPCell(new Phrase("20", font));
//        PdfPCell cell34 = new PdfPCell(new Phrase("Время срабатывания контакта, с", font));
//        cell34.setColspan(2); //объединение ячеек
//
//        //пятая строчка
//        PdfPCell cell40 = new PdfPCell(new Phrase("I_B2", font));
//        PdfPCell cell41 = new PdfPCell(new Phrase("50", font));
//        PdfPCell cell42 = new PdfPCell(new Phrase("f_B2", font));
//        PdfPCell cell43 = new PdfPCell(new Phrase("140", font));
//        PdfPCell cell44 = new PdfPCell(new Phrase("1", font));
//        PdfPCell cell45 = new PdfPCell(new Phrase("0,5", font));
//
//        //шестая строчка
//        PdfPCell cell50 = new PdfPCell(new Phrase("I_С2", font));
//        PdfPCell cell51 = new PdfPCell(new Phrase("50", font));
//        PdfPCell cell52 = new PdfPCell(new Phrase("f_С2", font));
//        PdfPCell cell53 = new PdfPCell(new Phrase("290", font));
//        PdfPCell cell54 = new PdfPCell(new Phrase("2", font));
//        PdfPCell cell55 = new PdfPCell(new Phrase("-", font));
//
//        //формируем строки
//        List<PdfPRow> rows = new ArrayList<>();
//        rows.add(new PdfPRow(new PdfPCell[]{cell00,cell01,cell02,cell03,cell04}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell10,cell11,cell12,cell13,cell14, cell15}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell20,cell21,cell22,cell23,cell24, cell25}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell30,cell31,cell32,cell33,cell34}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell40,cell41,cell42,cell43,cell44,cell45}));
//        rows.add(new PdfPRow(new PdfPCell[]{cell50,cell51,cell52,cell53,cell54,cell55}));
//
//        // Создаем таблицу и добавляем строки
//        PdfPTable table = new PdfPTable(5);
//        table.setWidthPercentage(100);
//        table.setSpacingBefore(10f);
//        table.setSpacingAfter(10f);
//
//        for (PdfPRow row : rows) {
//            for (PdfPCell cell : row.getCells()) {
//                table.addCell(cell);
//            }
//        }
//
//        return table;
    }













//    enum Scenarios {
//        Switcher, COMETRADE, DifferentialProtection, Protection1X, Protection3X, Hand
//    }
//
//    public void printProperReport(Scenarios scenario) {
//        switch (scenario) {
//            case Switcher -> reportForSwitcher();
//            case COMETRADE -> reportForCOMETRADE();
//            case DifferentialProtection -> reportForDifProtection();
//            case Protection1X -> reportForProtection1X();
//            case Protection3X -> reportForProtection3X();
//            case Hand -> reportForHand();
//        }
//    }
//
//
//
//
//    private void reportForDifProtection() {
//
//
//    }
//
//
//    private void reportForSwitcher() {
//
//    }
//
//
//    private void reportForCOMETRADE() {
//
//    }
//
//
//    private void reportForProtection1X() {
//
//    }
//
//
//    private void reportForProtection3X() {
//
//    }
//
//
//    private void reportForHand() {
//
//    }
