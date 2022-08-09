package com.xml.yaml.converter.controller;

import com.xml.yaml.converter.controller.command.Command;
import com.xml.yaml.converter.util.CommandNames;
import com.xml.yaml.converter.view.ConsoleView;
import com.xml.yaml.converter.view.View;

import java.util.Scanner;

public class CommandManager {
    private static final String ENTER_FILE_PATH = "enter.file.path";
    private static final String EXIT = "exit";
    private final View view = ConsoleView.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private final CommandRegistry commandRegistry = CommandRegistry.getInstance();

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            view.printText(ENTER_FILE_PATH);
            view.printCommand(EXIT, CommandNames.EXIT);
            String input = scanner.nextLine();
            Command command = commandRegistry.getCommand(input);
            isRunning = command.execute();
        }
    }
}
