package com.xml.yaml.converter.view;

import com.xml.yaml.converter.util.PropertyManager;

public class ConsoleView implements View {
    private static ConsoleView instance;
    private static final PropertyManager MANAGER = PropertyManager.getInstance();

    @Override
    public void printText(String text) {
        print(message(text));
    }
    private void print(String text){
        System.out.println(text);
    }

    public void printCommand(String comment, String command) {
        String text = String.format("%s - '%s':", message(comment), command);
        print(text);
    }

    private static String message(String message) {
        return MANAGER.getMessage(message);
    }

    public static ConsoleView getInstance() {
        if (instance == null) {
            instance = new ConsoleView();
        }
        return instance;
    }
}