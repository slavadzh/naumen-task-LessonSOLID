package ru.urfu.command;

import org.springframework.stereotype.Component;
import ru.urfu.document.DocumentService;

import java.util.Scanner;

/**
 * Команда создания документа
 */
@Component
public class CreateCommand implements Command {

    private final DocumentService documentService;

    /**
     * Создает команду create
     */
    public CreateCommand(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public void execute() {
        //Создаёт документ через ввод данных в консоли.
        //Сначала пользователь вводит имя, затем — содержимое документа.
        //Ввод содержимого продолжается до пустой строки.

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя документа: ");
        String name = scanner.nextLine().trim();

        System.out.println("Введите содержимое документа (пустая строка — завершить ввод):");

        StringBuilder content = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break; // окончание ввода
            content.append(line).append(System.lineSeparator());
        }

        documentService.createDocument(name, content.toString());

        System.out.println("Документ создан и сохранён в памяти.");
    }
}
