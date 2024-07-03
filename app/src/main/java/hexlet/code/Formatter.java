package hexlet.code;
import com.fasterxml.jackson.core.JsonProcessingException;
import formatters.Stylish;
import formatters.Plain;
import formatters.Json;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> rawCompareList, String format)
            throws JsonProcessingException {
        switch (format) {
            case "stylish":
                return Stylish.format(rawCompareList);
            case "plain":
                return Plain.format(rawCompareList);
            case "json":
                return Json.format(rawCompareList);
            default:
                throw new RuntimeException("\nUnsupported style format: " + format);
        }
    }
}
