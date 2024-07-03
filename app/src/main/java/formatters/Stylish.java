package formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    public static String format(List<Map<String, Object>> rawCompareList) {
        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");

        for (Map<String, Object> map : rawCompareList) {
            String key = map.get("key").toString();
            String oldValue = map.get("oldValue") != null ? map.get("oldValue").toString() : "null";
            String newValue = map.get("newValue") != null ? map.get("newValue").toString() : "null";
            String keyStatus = map.get("keyStatus").toString();

            switch (keyStatus) {
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
                    throw new RuntimeException("\nUnsupported key status: " + keyStatus);
            }
        }

        return result.toString();
    }
}
