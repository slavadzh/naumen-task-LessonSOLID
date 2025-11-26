package ru.urfu.command;

import org.springframework.stereotype.Component;
import ru.urfu.document.DocumentService;
import ru.urfu.utils.ExportService;
import ru.urfu.document.Document;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

/**
 * Команда экспорта в документ
 */
@Component
public class ExportCommand implements Command {

    private final DocumentService documentService;
    private final ExportService exportService;
    private final Path outputDir = Path.of(System.getProperty("user.home"), "lessonSOLID");

    /**
     * Создает команду export
     */
    public ExportCommand(DocumentService documentService, ExportService exportService) {
        this.documentService = documentService;
        this.exportService = exportService;
    }

    @Override
    public String getName() {
        return "export";
    }

    @Override
    public void execute() {
        //Экспортирует выбранный документ в указанный формат

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер документа: ");
        int index = Integer.parseInt(scanner.nextLine());

        Optional<Document> documentOptional = documentService.getDocument(index);
        if (documentOptional.isEmpty()) {
            System.out.println("Нет документа с таким номером.");
            return;
        }

        Document document = documentOptional.get();

        System.out.print("Введите формат: ");
        String format = scanner.nextLine().trim().toLowerCase();

        try {
            Files.createDirectories(outputDir);
            String outputPath = outputDir.resolve(document.name() + "." + format).toString();
            exportService.export(format, outputPath, document.content());
            System.out.println("Экспорт выполнен: " + outputPath);
        } catch (Exception e) {
            System.out.println("Ошибка экспорта: " + e.getMessage());
        }
    }
}
