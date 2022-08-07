package com.xml.yaml.converter.controller;

import com.xml.yaml.converter.view.ConsoleView;
import com.xml.yaml.converter.view.View;

public class Program {

    private final View view = ConsoleView.getInstance();
    private final CommandManager manager = new CommandManager();
    private static final String HELLO = "hello";

    public void run() {
        view.printText(HELLO);
        manager.run();
    }
}
