package com.xml.yaml.converter.model.service;

public interface FileService {
    void parsingXmlToYaml(String path);

    boolean existFile(String path);
}
