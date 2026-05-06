package utils;

public class MaskingUtils {
    public static String mask(String input) {

        if (input == null) return null;

        return input
                .replaceAll("(?i)\"password\"\\s*:\\s*\".*?\"", "\"password\":\"****\"")
                .replaceAll("(?i)\"token\"\\s*:\\s*\".*?\"", "\"token\":\"****\"")
                .replaceAll("(?i)\"email\"\\s*:\\s*\".*?\"", "\"email\":\"****\"");
    }
}
