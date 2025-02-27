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

import static org.example.reportForDifProtection.createReportForDifProtection;


public class Main {
    /** Это проект по генерации пдф отчетов основного проекта.
     * В нем содержится следующая иерархия работы файлов:
     * Внутренние настройки
     * Классы для генерации элементов пдф отчета: TableCreator,
     * Класс для генерации отчетов ПДФ.
     */

    enum Scenarios {
        Switcher, COMETRADE, DifferentialProtection, Protection1X, Protection3X, Hand
    }

//    public static void printProperReport (Scenarios scenario) {
//        switch (scenario) {
//            case DifferentialProtection -> new reportForDifProtection;
//            case Switcher -> reportForSwitcher();
//            case COMETRADE -> reportForCOMETRADE();
//            case Protection1X -> reportForProtection1X();
//            case Protection3X -> reportForProtection3X();
//            case Hand -> reportForHand();
//        }
//    }
    public static void main(String[] args) throws DocumentException, IOException {

        ReportGenerator.generateReport();

    }
}

//задача на завтра. Сделать отчет нормально для ДЗ.



















