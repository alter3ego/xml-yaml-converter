package com.xml.yaml.converter.controller.command;

import com.xml.yaml.converter.model.FileService;
import com.xml.yaml.converter.model.exception.AlreadyExistingYamlException;
import com.xml.yaml.converter.model.exception.FileSystemException;
import com.xml.yaml.converter.view.View;

import java.util.Scanner;

public class ParsingXmlToYamlCommand extends AbstractCommand {
    public static final String FILE_NOT_FOUND = "file.not.found";
    public static final String YAML_ALREADY_EXIST = "yaml.already.exist";
    public static final String FILE_SYSTEM_ERROR = "file.system.error";
    private final String path;

    public ParsingXmlToYamlCommand(FileService fileService, View view, Scanner scanner, String path) {
        super(fileService, view, scanner);
        this.path = path;
    }

    @Override
    public boolean execute() {
        if (!getFileService().existFile(path)) {
            getView().printText(FILE_NOT_FOUND);
            return true;
        }
        try {
            getFileService().parsingXmlToYaml(path);
        } catch (AlreadyExistingYamlException e) {
            getView().printText(YAML_ALREADY_EXIST);
        } catch (FileSystemException e){
            getView().printText(FILE_SYSTEM_ERROR);
        }
        return true;
    }
}
