package edu.moravian.Delivery;

public class CommandFormatter {
    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(prefix);
    }

    public static String getCommandSignature(String command) {
        if (isPrefix(command, "!") && command.length() > 1) {
            String[] parts = command.split("!");
            return parts[1];
        } else {
            return "";
        }
    }
}
