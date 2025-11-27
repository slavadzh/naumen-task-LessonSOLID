package ru.urfu.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Экспортёр текста в TXT
 */
@Component
public class TxtExporter implements Exporter {

    @Override
    public String getFormat() {
        return "txt";
    }

    @Override
    public void export(String outputPath, String content)  {
        try {
            Files.writeString(Path.of(outputPath), content);
        } catch (IOException e) {
            System.out.println("Ошибка экспорта: " + e.getMessage());
        }
    }
}
