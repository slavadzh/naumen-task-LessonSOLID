package ru.urfu.utils;

import java.io.IOException;

/**
 * Экспортёр документов
 */
public interface Exporter {

    /**
     * Получить формат документа
     */
    String getFormat();

    /**
     * Экспорт
     */
    void export(String outputPath, String content) throws IOException;
}
