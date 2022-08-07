package com.xml.yaml.converter.model.service.impl;

import com.xml.yaml.converter.model.service.ParsingService;
import com.xml.yaml.converter.model.exception.FileSystemException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParsingServiceImpl implements ParsingService {

    public static final String START_YAML_FILE = "---\n";
    public static final String START_REPEATABLE_PART = "%";

    public String parseDoc(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            document = dbf.newDocumentBuilder().parse(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new FileSystemException();
        }

        NodeList childNodes = document.getChildNodes();
        int level = 0;
        StringBuffer sb = new StringBuffer(START_YAML_FILE);
        returnChildNodes(childNodes, level, sb, START_REPEATABLE_PART);

        return sb.toString();
    }

    private void returnChildNodes(NodeList nodeList, int level, StringBuffer sb, String repeatablePart) {

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (isElementNode(node)) continue;
            if (repeatablePart.equals(node.getNodeName())) {
                sb.append(spaces(level)).append("-");
            } else {
                sb.append(spaces(level)).append(node.getNodeName()).append(":");
                checkRepeatableElement(nodeList, level, sb, i, node);

            }
            repeatablePart = node.getNodeName();

            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                for (int j = attributes.getLength() - 1; j >= 0; j--) {

                    String attributeName = node.getAttributes().item(j).getNodeName();
                    String attributeValue = node.getAttributes().item(j).getTextContent();

                    sb.append("\n").append(spaces(level + 1))
                            .append("\"-").append(attributeName)
                            .append("\": ").append(attributeValue);
                }

            }
            if (node.getChildNodes().getLength() == 1) {
                sb.append(" ").append(node.getTextContent());
            }
            sb.append("\n");
            returnChildNodes(node.getChildNodes(), level + 1, sb, repeatablePart);
        }
    }

    private void checkRepeatableElement(NodeList parentNodeList, int level, StringBuffer sb,
                                        int nodeListElement, Node currentNode) {
        for (int j = nodeListElement + 1; j < parentNodeList.getLength(); j++) {
            Node nextNode = parentNodeList.item(j);
            if (isElementNode(nextNode)) continue;
            if (nextNode.getNodeName().equals(currentNode.getNodeName())) {
                sb.append("\n").append(spaces(level)).append("-");
            }
            break;
        }
    }

    private boolean isElementNode(Node node) {
        return node.getNodeType() != Node.ELEMENT_NODE;
    }

    private String spaces(int number) {
        return "" + "  ".repeat(Math.max(0, number));
    }
}
