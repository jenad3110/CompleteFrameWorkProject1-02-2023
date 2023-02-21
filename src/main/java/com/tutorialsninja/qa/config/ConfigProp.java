package com.tutorialsninja.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProp {


    public static String currentDir = System.getProperty("user.dir");


    public static Properties loadProperties() {


        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(currentDir+"/src/main/java/com/tutorialsninja/qa/config/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
