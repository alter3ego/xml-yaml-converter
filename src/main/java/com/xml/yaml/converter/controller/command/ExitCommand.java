package com.xml.yaml.converter.controller.command;

import com.xml.yaml.converter.model.FileService;
import com.xml.yaml.converter.view.View;

import java.util.Scanner;

public class ExitCommand extends AbstractCommand {

    public static final String GOODBYE = "goodbye";

    public ExitCommand(FileService fileService, View view, Scanner scanner) {
        super(fileService, view, scanner);
    }

    @Override
    public boolean execute() {
        getView().printText(GOODBYE);
        return false;
    }
}
