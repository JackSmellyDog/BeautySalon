package com.kpi.salon.utils;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourcesManager {
    private static final Logger LOGGER = Logger.getLogger(ResourcesManager.class);

    public Properties getProperties(String filename) throws IOException {
        if (!filename.endsWith(".properties")) {
            filename += ".properties";
        }

        Properties properties = new Properties();
        properties.load(new FileReader(getPathToResource(filename)));

        return properties;
    }

    private String getPathToResource(String filename) throws FileNotFoundException {
        URL url = getClass().getClassLoader().getResource(filename);

        if (url == null)
            throw new FileNotFoundException();

        return url.getFile();
    }
}
