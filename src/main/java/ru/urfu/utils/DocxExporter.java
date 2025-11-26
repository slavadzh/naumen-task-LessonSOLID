package ru.urfu.utils;

import org.springframework.stereotype.Component;

/**
 * Экспортёр текста в WORD
 *
 * Чтобы добавить новый формат, надо создать класс Экспортера под него
 * и реализовать методы Exporter
 *
 * Spring сам подтянет данный класс в ExportService
 */
@Component
public class DocxExporter implements Exporter {

    @Override
    public String getFormat() { return "docx"; }

    @Override
    public void export(String outputPath, String content) {
        // логика генерации docx
    }
}

