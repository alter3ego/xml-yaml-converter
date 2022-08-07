package com.xml.yaml.converter.model;

public interface FileService {
    void parsingXmlToYaml(String path);

    boolean existFile(String path);
}
