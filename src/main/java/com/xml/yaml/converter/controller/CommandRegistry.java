package com.xml.yaml.converter.controller;

import com.xml.yaml.converter.controller.command.Command;
import com.xml.yaml.converter.controller.command.ExitCommand;
import com.xml.yaml.converter.controller.command.ParsingXmlToYamlCommand;
import com.xml.yaml.converter.model.FileService;
import com.xml.yaml.converter.model.impl.FileServiceImpl;
import com.xml.yaml.converter.util.CommandNames;
import com.xml.yaml.converter.view.ConsoleView;
import com.xml.yaml.converter.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandRegistry {
    private static CommandRegistry instance;
    private final Map<String, Command> commands = new HashMap<>();
    private static final FileService FILE_SERVICE = new FileServiceImpl();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final View VIEW = ConsoleView.getInstance();

    private CommandRegistry() {
        commands.put(CommandNames.EXIT, new ExitCommand(FILE_SERVICE, VIEW, SCANNER));
    }

    public Command getCommand(String input) {
        Command command = commands.get(input);
        if (command == null) {
            command = new ParsingXmlToYamlCommand(FILE_SERVICE, VIEW, SCANNER, input);
        }
        return command;
    }

    public static CommandRegistry getInstance() {
        if (instance == null) {
            instance = new CommandRegistry();
        }
        return instance;
    }
}
