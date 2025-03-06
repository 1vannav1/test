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
    //енам сценариев
    enum Scenarios {
        Switcher,
        DifferentialProtection
    }

    public static void main(String[] args) throws DocumentException, IOException {

        Scenarios scenario = Scenarios.DifferentialProtection;

        String[] element0 = new String[]{"SwitcherData.pdf"};
        String[] element1 = new String[]{"Название режима"};
        String[] element2 = new String[]{"01.01.2025", "12.00.00", "Т-16-У3", "Иванов Иван Иванович", "D-0/y-1", "ABG"};
        String[] element3 = new String[]{"101", "10", "102", "130", "103", "250", "51", "20", "52", "160", "53", "280","да", "нет"};
        String[] element4 = new String[]{"Аварий нет"};

        String[][] SwitcherData = new String[][]{element0, element1, element2, element3, element4};


        String[] element01 = new String[]{"DifferentialProtection.pdf"};
        String[] element11 = new String[]{"ДИФФЕРЕНЦИАЛЬНАЯ ЗАЩИТА"};
        String[] element21 = new String[]{"01.01.2025", "12.00.00", "Т-16-У3", "Иванов Иван Иванович", "D-0/y-1", "ABG"};
        String[] element31 = new String[]{"101", "10", "102", "130", "103", "250", "51", "20", "52", "160", "53", "280","да", "нет"};
        String[] element41 = new String[]{"Аварий нет"};

        String[][] DifferentialProtectionData = new String[][]{element01, element11, element21, element31, element41};

        switch (scenario) {
            case Switcher:
                ReportGenerator.generateReport(SwitcherData);
                break;
            case DifferentialProtection:
                ReportGenerator.generateReport(DifferentialProtectionData);
                break;
            default:
                System.out.println("Введен неправильный сценарий");

        }
    }
}

