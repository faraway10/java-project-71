package hexlet.code;
import formatters.Stylish;
import formatters.Plain;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> rawCompareList, String format) {
        switch (format) {
            case "stylish":
                return Stylish.format(rawCompareList);
            case "plain":
                return Plain.format(rawCompareList);
            default:
                throw new RuntimeException("\nUnsupported style format: " + format);
        }
    }
}
