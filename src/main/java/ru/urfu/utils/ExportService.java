package ru.urfu.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для управления экспортёрами документов
 *
 * Позволяет выполнять экспорт документов в разные форматы
 */
@Service
public class ExportService {

    /**
     * Хранилище экспортёров
     * ключ — формат
     * значение — объект экспортёра, реализующий интерфейс {@link Exporter}
     */
    private final Map<String, Exporter> exporters = new HashMap<>();

    /**
     * Создаёт сервис экспорта и регистрирует все доступные экспортёры
     */
    public ExportService(List<Exporter> exportersList) {
        for (Exporter exporter : exportersList) {
            exporters.put(exporter.getFormat(), exporter);
        }
    }

    /**
     * Выполняет экспорт документа в указанный формат
     *
     */
    public void export(String format, String outputPath, String content) {
        Exporter exporter = exporters.get(format);

        if (exporter == null) {
            System.out.println("Неизвестный формат: " + format);
            return;
        }

        try {
            exporter.export(outputPath, content);
        } catch (Exception e) {
            System.out.println("Ошибка при экспорте: " + e.getMessage());
        }
    }
}
