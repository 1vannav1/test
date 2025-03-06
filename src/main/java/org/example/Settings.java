package org.example;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;

public class Settings {
    /**
     * Класс настроек для подгрузки шрифтов и возможно других необходимых элементов для генерации отчета.
     */


    private BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    private Font font;

    // Конструктор
    public Settings() throws DocumentException, IOException {

    }

    // Геттер для получения значения font
    public Font getFont(int v) {
        setFont(v);
        return font;
    }

    // Сеттер для установки значения font
    public void setFont(int v) {
         font = new Font(baseFont, v, Font.NORMAL);
    }

}
