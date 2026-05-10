package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {

        try {

            String env = System.getProperty("env", "dev");

            String fileName = "config/" + env + ".properties";

            InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public static String getToken() {
        return EnvReader.get("TOKEN");
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(properties.getProperty("ENABLE_LOG"));
    }
}
