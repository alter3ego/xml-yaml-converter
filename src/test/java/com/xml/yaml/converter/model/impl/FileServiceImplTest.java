package com.xml.yaml.converter.model.impl;

import com.xml.yaml.converter.model.service.FileService;
import com.xml.yaml.converter.model.service.impl.FileServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileServiceImplTest {
    private final String[][] testCases = new String[][]{
            {
                    "src/test/resources/input/simple.xml",  //input
                    "src/test/resources/input/simple.yaml", //drop
                    "src/test/resources/output/simple.yaml" //output
            },
            {
                    "src/test/resources/input/medium.xml",
                    "src/test/resources/input/medium.yaml",
                    "src/test/resources/output/medium.yaml"
            },
            {
                    "src/test/resources/input/hard.xml",
                    "src/test/resources/input/hard.yaml",
                    "src/test/resources/output/hard.yaml"
            },
            {
                    "src/test/resources/input/overHard.xml",
                    "src/test/resources/input/overHard.yaml",
                    "src/test/resources/output/overHard.yaml"
            },
    };
    @Test
    void parsingXmlToYamlSimple() throws IOException {
        parsingXmlToYaml(testCases[0]);
    }
    @Test
    void parsingXmlToYamlMedium() throws IOException {
        parsingXmlToYaml(testCases[1]);
    }
    @Test
    void parsingXmlToYamlHard() throws IOException {
        parsingXmlToYaml(testCases[2]);
    }
    @Test
    void parsingXmlToYamlOverHard() throws IOException {
        parsingXmlToYaml(testCases[3]);
    }


    void parsingXmlToYaml(String[] s) throws IOException {
            preparing(s[1]);
            doParsingTest(s);
    }

    void doParsingTest(String[] params) throws IOException {
        FileService fileService = new FileServiceImpl();
        fileService.parsingXmlToYaml(params[0]);
        File ymlFileActual = new File(params[1]);
        File ymlFileExpected = new File(params[2]);
        String expected = getStringFromFile(ymlFileExpected);
        String actual = getStringFromFile(ymlFileActual);
        testCase(expected, actual);
    }

    private void preparing(String path) {
        File ymlFile = new File(path);
        if (ymlFile.exists()) {
            ymlFile.delete();
        }
    }

    private String getStringFromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = reader.readLine();
        }
        return sb.toString();
    }

    private void testCase(final String expected, final String actual) {
        assertEquals(expected, actual);
    }
}