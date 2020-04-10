package com.ozon.utils;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static String propFileName = "config.properties";
    private static InputStream inputStream;
    private static final Logger logger = Logger.getLogger(PropertyLoader.class);

    public static String getProperty(String... fields) {
        StringBuilder url = new StringBuilder();
        try {
            Properties properties = new Properties();
            inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
                for (String field : fields) {
                    url.append(properties.getProperty(field));
                }
                inputStream.close();
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (IOException e) {
            logger.trace(e.getMessage());
        }
        return url.toString();
    }
}
