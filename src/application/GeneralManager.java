package application;

public class GeneralManager {
    public static boolean empty(String string) {
        // return true if parameter string is found empty.
        return string == null || string.trim().isEmpty();
    }
}
