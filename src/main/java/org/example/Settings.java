package org.example;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;

public class Settings {
    /**
     * Класс настроек для подгрузки шрифтов и возможно других необходимых элементов для генерации отчета.
     */

    int v;

    BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font font = new Font(baseFont, v, Font.NORMAL);

    // Конструктор
    public Settings(int v) throws DocumentException, IOException {
        this.v = v;
    }

    // Геттер для получения значения font
    public Font getFont() {
        return font;
    }

    // Сеттер для установки значения font
    public void setFont(int v) {

        this.v = v;
    }

}
