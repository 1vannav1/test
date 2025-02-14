//package org.example;
//
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class reportForCOMETRADE {
//
//    private static void reportForCOMETRADE() {
//        Document document = new Document();
//
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream("protocol_cometrade.pdf"));
//            document.open();
//
//            //добавляем русский шрифт
//            BaseFont baseFont = BaseFont.createFont("ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font titleFont = new Font(baseFont,  18, Font.NORMAL);
//
//            // Добавляем заголовок
//            Paragraph title = new Paragraph("ПРОТОКОЛ COMETRADE", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//
//            // Добавляем дату и время
//            Font dateFont = new Font(baseFont, 12, Font.NORMAL);
//            Paragraph date = new Paragraph("Дата:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
//                    "       Время:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
//                    dateFont);
//            document.add(date);
//
//            // Добавляем ФИО работника
//            Paragraph worker = new Paragraph("ФИО работника: Иванов Иван Иванович", dateFont);
//            document.add(worker);
//
//            // Добавляем название оборудования
//            Paragraph equipment = new Paragraph("Название оборудования: Т-16-У3", dateFont);
//            document.add(equipment);
//
//            // Добавляем форму проверки
//            Paragraph checkForm = new Paragraph("Форма проверки: COMETRADE.", dateFont);
//            document.add(checkForm);
//
//            // Добавляем вид повреждения
//            Paragraph damageType = new Paragraph("Вид повреждения: внешнее двухфазное КЗ на землю.", dateFont);
//            document.add(damageType);
//
//            // Добавляем таблицу 1
//            PdfPTable table1 = new PdfPTable(2);
//            table1.setWidthPercentage(50);
//            table1.setSpacingBefore(10f);
//            table1.setSpacingAfter(10f);
//
//            //запись построчно
//            String[] rowData0Table1 = {"Альфа","<A>", "1",""};
//            addTableRow(table1, rowData0Table1, dateFont);
//            String[] rowData1Table1 = {"Бета","<Б>", "2",""};
//            addTableRow(table1, rowData1Table1, dateFont);
//            String[] rowData2Table1 = {"Гамма","<Г>", "3",""};
//            addTableRow(table1, rowData2Table1, dateFont);
//            String[] rowData3Table1 = {"Штрих", "</>", "4",""};
//            addTableRow(table1, rowData3Table1, dateFont);
//
//            document.add(table1);
//
//            // Добавляем таблицу 2
//            PdfPTable table2 = new PdfPTable(6);
//            table2.setWidthPercentage(100);
//            table2.setSpacingBefore(10f);
//            table2.setSpacingAfter(10f);
//
//            //Создаю ячейки поочереди, так как только так их можно объединить
//            table2.addCell(createIndexedCell("I", "A1", dateFont));
//            table2.addCell(createIndexedCell("100", "", dateFont));
//            table2.addCell(createIndexedCell("f", "A1", dateFont));
//            table2.addCell(createIndexedCell("10", "", dateFont));
//            PdfPCell mergedCell04 = new PdfPCell(new Phrase("Сработавшие контакты", dateFont));
//            mergedCell04.setColspan(2);
//            table2.addCell(mergedCell04);
//
//            String[] rowData1Table2 = {"I","B1", "100","", "f", "B1", "130","", "1","", "2",""};
//            addTableRow(table2, rowData1Table2, dateFont);
//            String[] rowData2Table2 = {"I", "C1", "100","", "f", "C1", "280", "", "сработал", "", "нет",""};
//            addTableRow(table2, rowData2Table2, dateFont);
//
//            //Создаю ячейки поочереди, так как только так их можно объединить
//            table2.addCell(createIndexedCell("I", "A2", dateFont));
//            table2.addCell(createIndexedCell("50", "", dateFont));
//            table2.addCell(createIndexedCell("f", "A2", dateFont));
//            table2.addCell(createIndexedCell("20", "", dateFont));
//            PdfPCell mergedCell44 = new PdfPCell(new Phrase("Время срабатывания контакта, c", dateFont));
//            mergedCell44.setColspan(2);
//            table2.addCell(mergedCell44);
//
//            String[] rowData5Table2 = {"I","B2", "50","", "f","B2", "140","", "1","", "0.5",""};
//            addTableRow(table2, rowData5Table2, dateFont);
//            String[] rowData6Table2 = {"I","C2", "50","", "f", "C2", "290","", "2","", "-",""};
//            addTableRow(table2, rowData6Table2, dateFont);
//
//            document.add(table2);
//            document.add(getTestTable(dateFont));
//
//            // Добавляем информацию об ошибках
//            Paragraph errors = new Paragraph("Зарегистрированные ошибки: нет", dateFont);
//            document.add(errors);
//
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//            System.out.println("Файл создан!");
//        }
//    }
//}
