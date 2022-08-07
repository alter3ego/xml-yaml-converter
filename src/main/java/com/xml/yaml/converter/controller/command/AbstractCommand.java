package com.xml.yaml.converter.controller.command;

import com.xml.yaml.converter.model.service.FileService;
import com.xml.yaml.converter.view.View;

import java.util.Scanner;

abstract public class AbstractCommand implements Command {
    private final FileService fileService;
    private final Scanner scanner;
    private final View view;

    AbstractCommand(FileService fileService, View view, Scanner scanner) {
        this.fileService = fileService;
        this.view = view;
        this.scanner = scanner;
    }

    public FileService getFileService() {
        return fileService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public View getView() {
        return view;
    }
}
