package ru.urfu.command;

import org.springframework.stereotype.Component;
import ru.urfu.document.DocumentService;

/**
 * Команда вывода документов
 */
@Component
public class ListCommand implements Command {

    private final DocumentService documentService;

    /**
     * Создает команду list
     */
    public ListCommand(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void execute() {
        //Выводит список документов

        var documents = documentService.list();
        if (documents.isEmpty()) {
            System.out.println("Документов нет.");
            return;
        }
        for (int i = 0; i < documents.size(); i++) {
            System.out.println(i + ": " + documents.get(i).name());
        }
    }
}
