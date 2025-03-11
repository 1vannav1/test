package org.example;

//import com.aspose.pdf.Document;

import com.lowagie.text.*;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    /** Это проект по генерации пдф отчетов основного проекта. В нем содержится следующая иерархия работы файлов:
     * Внутренние настройки
     * Классы для генерации элементов пдф отчета: TableCreator,
     * Класс для генерации отчетов ПДФ.
     *.
     * Сейчас генерируется только один отчет - диф/защита. Это связано с тем, что неизвестен конструктор других отчетов
     * Для того, чтобы добавить другие отчеты, необходимо добавить их в енам. Также стоит перестроить конструктор таблиц,
     * чтобы размер и данные генерировались в зависимости от Scenarios scenario
     */
    //енам сценариев
    enum Scenarios {
        Switcher,
        DifferentialProtection
    }

    public static void main(String[] args) throws DocumentException, IOException {

        // Данные который должны получаться от буфера.
        Scenarios scenario = Scenarios.DifferentialProtection;
        String[] fileName = new String[]{"DifferentialProtection.pdf"};
        String[] headerElement = new String[]{"ДИФФЕРЕНЦИАЛЬНАЯ ЗАЩИТА"};
        String[] scenarioInfoElement = new String[]{"01.01.2025", "12.00.00", "Т-16-У3", "Иванов Иван Иванович", "D-0/y-1", "ABG"};
        String[] scenarioTableElement = new String[]{"101", "10", "102", "130", "103", "250", "51", "20", "52", "160", "53", "280","да", "нет"};
        String[] errorsElement = new String[]{"Аварий нет"};

        String[][] dataForCreatePDF = new String[][]{fileName, headerElement, scenarioInfoElement, scenarioTableElement, errorsElement};

        // Оператор для генерации отчета.
        switch (scenario) {
            case Switcher, DifferentialProtection:
                ReportGenerator.generateReport(dataForCreatePDF);
                break;
            default:
                System.out.println("Введен неправильный сценарий");

        }
    }
}

