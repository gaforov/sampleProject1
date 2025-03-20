package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
        private final static Properties properties;

        static {
            try {
                var fileInputStream = new FileInputStream("src/resources/configs/config.properties");
                properties = new Properties();
                properties.load(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
