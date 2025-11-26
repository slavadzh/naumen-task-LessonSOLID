package ru.urfu.command;

import org.springframework.stereotype.Component;
import ru.urfu.document.DocumentService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Команда импорта
 */
@Component
public class ImportCommand implements Command{

    private final DocumentService documentService;

    /**
     * Создает команду import
     */
    public ImportCommand(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public String getName() {
        return "import";
    }

    @Override
    public void execute() {
        //Импортирует документ из текстового файла
        //Путь вводится пользователем

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к txt файлу: ");
        String path = scanner.nextLine();

        try {
            documentService.importTxt(path);
            System.out.println("Импорт выполнен.");
        } catch (IOException e) {
            System.out.println("Ошибка импорта: " + e.getMessage());
        }
    }
}
