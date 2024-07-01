package hexlet.code;

public class Formatter {
    public static String format(Object obj, String format) {
        return obj != null ? obj.toString() : "null";
    }
}
