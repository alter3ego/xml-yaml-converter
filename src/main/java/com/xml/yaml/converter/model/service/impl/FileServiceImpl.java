package com.xml.yaml.converter.model.service.impl;

import com.xml.yaml.converter.model.service.FileService;
import com.xml.yaml.converter.model.service.ParsingService;
import com.xml.yaml.converter.model.exception.AlreadyExistingYamlException;
import com.xml.yaml.converter.model.exception.FileSystemException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileServiceImpl implements FileService {
    public static final String REGEX_YAML_SPLIT = "\\.";
    public static final String YAML = ".yaml";

    private final ParsingService parsingService = new ParsingServiceImpl();

    @Override
    public void parsingXmlToYaml(String path) {
        String str = splitPathXmlToYaml(path);
        try {
            parseXmlToNewYaml(path, str);
        } catch (IOException e) {
            throw new FileSystemException();
        }
    }

    private String splitPathXmlToYaml(String path) {
        return path.split(REGEX_YAML_SPLIT)[0] + YAML;
    }

    private void parseXmlToNewYaml(String xmlPath, String ymlPath) throws IOException {
        uniqueYamlFile(ymlPath);
        File xmlFile = new File(xmlPath);
        File ymlFile = new File(ymlPath);
        String parseResult = parsingService.parseDoc(xmlFile);
        writeToFile(ymlFile, parseResult);
    }


    private void writeToFile(File file, String string) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(string);
        writer.flush();
        writer.close();
    }

    private void uniqueYamlFile(String ymlPath) {
        if (existFile(ymlPath))
            throw new AlreadyExistingYamlException();
    }

    @Override
    public boolean existFile(String path) {
        File file = new File(path);
        return file.exists();
    }
}
