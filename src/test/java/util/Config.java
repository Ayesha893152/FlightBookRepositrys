package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties"; // ✅ Classpath-relative path
    private static Properties properties;

    public static void initialize() {
        properties = loadProperties();

        // Check for any override from system properties
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        // Print loaded properties
        log.info("Test Properties");
        log.info("-------------------------");
        for (String key : properties.stringPropertyNames()) {
            log.info("{}={}", key, properties.getProperty(key));
        }
        log.info("-------------------------");
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ResourceLoader.getresources(DEFAULT_PROPERTIES)) {
            if (stream == null) {
                throw new RuntimeException("Properties file not found in classpath: config/default.properties");
            }
            properties.load(stream);
        } catch (Exception e) {
            log.error("Unable to read the properties file: config/default.properties", e);
            throw new RuntimeException("Failed to load properties", e);
        }
        return properties;
    }
}