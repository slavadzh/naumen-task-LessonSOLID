package ru.urfu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import ru.urfu.command.Command;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 1. Принцип единой ответственности:
 *
 *  Нарушение: Класс ConsoleApp выполнял слишком много задач -
 *  выполнение каждой команды в одном классе
 *
 *  Исправление: Созданы отдельные классы для каждой команды,
 *  реализующие интерфейс Command
 *
 *  Теперь каждая команда отвечает за одну задачу, а у ConsoleApp — только за ввод команд
 *
 *  2. Принцип открытости/закрытости
 *
 *  Нарушение: Для добавления нового формата экспорта приходилось изменять существующий код
 *
 *  Исправление: Создан интерфейс Exporter и сервис ExportService, который работает
 *  с любыми реализациями Exporter. Добавление нового формата требует только создания
 *  нового класса, реализующего Exporter, без изменения существующего кода
 *
 *  Аналогично, выбор команд реализован через интерфейс Command,
 *  что устранило зависимость от switch/case
 *
 * 3. Принцип инверсии зависимостей:
 *
 *  Нарушение: ConsoleApp напрямую зависел от конкретных реализаций Exporter (PdfExporter, TxtExporter)
 *
 *  Исправление: ConsoleApp и ExportService зависят только от интерфейсов (Command и Exporter)
 */

/**
 * Основной класс консольного приложения.
 * Реализует ввод команд и взаимодействие с сервисами.
 */
@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    private final Map<String,Command> commands;

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Создаёт основной класс консольного приложения и регистрирует команды
     *
     * @param commands список команд, которые будут доступны в консоли
     */
    @Autowired
    public ConsoleApp(List<Command> commands) {
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getName, command -> command));;
    }

    /**
     * Точка входа приложения.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Консольное приложение ===");

        while (true) {
            System.out.println("\nКоманды: import, list, create, export, exit");
            System.out.print("> ");
            String cmd = scanner.nextLine().trim();

            Command command = commands.get(cmd);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}
