package com.xml.yaml.converter.util;

import java.util.ResourceBundle;

public class PropertyManager {
    private static PropertyManager instance;
    private ResourceBundle messageBundle;

    private static final String BUNDLE_MESSAGE = "message";

    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
            instance.messageBundle =
                    ResourceBundle.getBundle(BUNDLE_MESSAGE);
        }
        return instance;
    }

    public String getMessage(String key) {
        return (String) messageBundle.getObject(key);
    }
}
