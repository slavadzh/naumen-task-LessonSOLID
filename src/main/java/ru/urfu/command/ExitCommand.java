package ru.urfu.command;

import org.springframework.stereotype.Component;

/**
 * Команда выхода
 */
@Component
public class ExitCommand implements Command {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void execute() {
        //Выходим из приложения
        System.exit(0);
    }
}
