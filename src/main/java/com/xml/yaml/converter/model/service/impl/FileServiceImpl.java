package com.xml.yaml.converter.model.service.impl;

import com.xml.yaml.converter.model.service.FileService;
import com.xml.yaml.converter.model.service.ParsingService;
import com.xml.yaml.converter.model.exception.AlreadyExistingYamlException;
import com.xml.yaml.converter.model.exception.FileSystemException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    private static final String SAMPLE_XML = "sample.xml";
    private static final String OUTPUT_YML = "output.yml";

    private final ParsingService parsingService = new ParsingServiceImpl();

    @Override
    public void parsingXmlToYaml(String path) {
        String xmlPath = folderPath(path) + SAMPLE_XML;
        String yamlPath = folderPath(path) + OUTPUT_YML;

        try {
            parseXmlToNewYaml(xmlPath, yamlPath);
        } catch (IOException e) {
            throw new FileSystemException();
        }
    }

    @Override
    public boolean existFile(String path) {
        File file = new File(path);
        return file.exists();
    }

    private String folderPath(String path) {
        int index = path.lastIndexOf("/");

        if (index == -1) {
            index = path.lastIndexOf("\\");
        }

        return path.substring(0, index + 1);
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
        if (existFile(ymlPath)) {
            throw new AlreadyExistingYamlException();
        }
    }
}
