package com.xml.yaml.converter.model.service.impl;

import com.xml.yaml.converter.model.service.ParsingService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingServiceImplTest {

    ParsingService parsingService = new ParsingServiceImpl();

    @ParameterizedTest
    @MethodSource("testCases")
    void testParseDoc(String desc,String inputFileName, String expectedPath) throws IOException {
        File inputFile = createFileFromPath(inputFileName);
        File expectedFile = createFileFromPath(expectedPath);
        String expected = getStringFromFile(expectedFile);

        String actual = parsingService.parseDoc(inputFile);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("simple flow","src/test/resources/input/simple.xml", "src/test/resources/output/simple.yaml"),
                Arguments.of("medium flow","src/test/resources/input/medium.xml", "src/test/resources/output/medium.yaml"),
                Arguments.of("hard flow","src/test/resources/input/hard.xml", "src/test/resources/output/hard.yaml"),
                Arguments.of("overHard flow","src/test/resources/input/overHard.xml", "src/test/resources/output/overHard.yaml")
        );
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

    private File createFileFromPath(String path) {
        return new File(path);
    }
}