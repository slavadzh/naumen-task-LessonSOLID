package ru.urfu.command;

/**
 * Команда приложения
 */
public interface Command {

    /**
     * Получить название команды
     */
    String getName();

    /**
     * Выполнить команду
     */
    void execute();
}
