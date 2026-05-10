package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {

        try {

            String env = System.getProperty("env", "staging");

            if (env == null || env.isEmpty()) {
                env = "staging";
            }

            String fileName = "config/" + env + ".properties";

            InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);

            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + fileName);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String get(String key) {

        // Priority 1: System ENV
        String value = System.getenv(key);

        if (value != null && !value.isEmpty()) {
            return value;
        }

        // Priority 2: properties file
        return properties.getProperty(key);
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
