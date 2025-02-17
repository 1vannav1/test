package org.example;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;

public class settings {

    /**
     * Класс настроек для подгрузки шрифтов и возможно других необходимых элементов для генерации отчета.
     */
      public static Font createFont() throws DocumentException, IOException {
      //добавляем русский шрифт
      BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      Font font = new Font(baseFont, 12, Font.NORMAL);

      return font;
    }
}
