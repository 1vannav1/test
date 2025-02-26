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
    //    enum Scenarios {
    //        Switcher, COMETRADE, DifferentialProtection, Protection1X, Protection3X, Hand
    //    }
    public static void main(String[] args) {

        //данные для отчета подгружаемые из буфера
        //1.Bufer.getScenarioData()
        int rows = 3;
        int columns = 6;
        //2.getProperName
        String properName = "testTable.pdf";
        //3.Отправка массива данных от сценария из буфера для создания 5 элементов:



        //создаем отчет
        createReportForDifProtection(rows, columns, properName);
    }
}
//    public static void printProperReport (Scenarios scenario){
//        switch (scenario) {
//            case DifferentialProtection -> new reportForDifProtection.;
//            case Switcher -> reportForSwitcher();
//            case COMETRADE -> reportForCOMETRADE();
//            case Protection1X -> reportForProtection1X();
//            case Protection3X -> reportForProtection3X();
//            case Hand -> reportForHand();


















