package formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String format(List<Map<String, Object>> rawCompareList) {
        StringJoiner result = new StringJoiner("\n");

        for (Map<String, Object> map : rawCompareList) {
            String key = map.get("key").toString();
            String oldValue = convertObjToStr(map.get("oldValue"));
            String newValue = convertObjToStr(map.get("newValue"));
            String keyStatus = map.get("keyStatus").toString();

            switch (keyStatus) {
                case "equal":
                    break;
                case "added":
                    result.add("Property '" + key + "' was added with value: " + newValue);
                    break;
                case "removed":
                    result.add("Property '" + key + "' was removed");
                    break;
                case "changed":
                    result.add("Property '" + key + "' was updated. From " + oldValue + " to " + newValue);
                    break;
                default:
                    throw new RuntimeException("\nUnsupported key status: " + keyStatus);
            }
        }

        return result.toString();
    }

    public static String convertObjToStr(Object obj) {
        if (obj instanceof String) {
            return "'" + obj.toString() + "'";
        } else if (obj instanceof Collection<?> || obj instanceof Map<?, ?>) {
            return "[complex value]";
        } else {
            return obj != null ? obj.toString() : "null";
        }
    }
}
