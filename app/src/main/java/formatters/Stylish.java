package formatters;

import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> rawCompareList) {
        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");

        for (String key : rawCompareList.keySet()) {
            Map<String, Object> map = rawCompareList.get(key);
            String oldValue = map.get("oldValue") != null ? map.get("oldValue").toString() : "null";
            String newValue = map.get("newValue") != null ? map.get("newValue").toString() : "null";
            String status = map.get("status").toString();

            switch (status) {
                case "equal":
                    result.add("    " + key + ": " + newValue);
                    break;
                case "added":
                    result.add("  + " + key + ": " + newValue);
                    break;
                case "removed":
                    result.add("  - " + key + ": " + oldValue);
                    break;
                case "changed":
                    result.add("  - " + key + ": " + oldValue);
                    result.add("  + " + key + ": " + newValue);
                    break;
                default:
                    throw new RuntimeException("\nUnsupported key status: " + status);
            }
        }

        return result.toString();
    }
}
