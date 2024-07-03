package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parseFileToMap(filePath1);
        Map<String, Object> map2 = Parser.parseFileToMap(filePath2);

        List<String> allKeys = new ArrayList<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        allKeys = allKeys.stream().distinct().sorted().toList();

        Map<String, Map<String, Object>> rawCompareResult = new LinkedHashMap<>();

        for (String key : allKeys) {
            boolean isEqual = Objects.equals(map1.get(key), map2.get(key));
            boolean isRemoved = map1.containsKey(key) && !map2.containsKey(key);
            boolean isAdded = !map1.containsKey(key) && map2.containsKey(key);
            boolean isChanged = !isEqual && !isRemoved && !isAdded;
            String status = null;

            if (isEqual) {
                status = "equal";
            } else if (isChanged) {
                status = "changed";
            } else if (isRemoved) {
                status = "removed";
            } else {
                status = "added";
            }

            Map<String, Object> map = new HashMap<>();
            map.put("status", status);
            map.put("oldValue", map1.get(key));
            map.put("newValue", map2.get(key));
            rawCompareResult.put(key, map);
        }

        return Formatter.format(rawCompareResult, format);
    }
}
