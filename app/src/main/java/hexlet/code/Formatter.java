package hexlet.code;
import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Json;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> compareResult, String format)
            throws JsonProcessingException {
        switch (format) {
            case "stylish":
                return Stylish.format(compareResult);
            case "plain":
                return Plain.format(compareResult);
            case "json":
                return Json.format(compareResult);
            default:
                throw new RuntimeException("\nUnsupported style format: " + format);
        }
    }
}
