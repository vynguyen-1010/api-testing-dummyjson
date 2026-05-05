package config;

public class ConfigManager {
    public static String getBaseUrl() {
        return EnvReader.get("BASE_URL");
    }

    public static String getToken() {
        return EnvReader.get("TOKEN");
    }
}
