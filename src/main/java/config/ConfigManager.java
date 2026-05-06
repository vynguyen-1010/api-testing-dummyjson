package config;

public class ConfigManager {
    public static String getBaseUrl() {
        return EnvReader.get("BASE_URL");
    }

    public static String getToken() {
        return EnvReader.get("TOKEN");
    }

    public static boolean isLoggingEnabled() {
        String value = EnvReader.get("ENABLE_LOG");
        return value != null && value.equalsIgnoreCase("true");
    }
}
