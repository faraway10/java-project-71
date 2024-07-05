package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Comparator {
    public static Map<String, Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        List<String> allKeys = new ArrayList<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        allKeys = allKeys.stream().distinct().sorted().toList();

        Map<String, Map<String, Object>> compareResult = new LinkedHashMap<>();

        for (String key : allKeys) {
            String status = null;

            if (Objects.equals(map1.get(key), map2.get(key))) {
                status = "equal";
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                status = "added";
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                status = "removed";
            } else {
                status = "changed";
            }

            Map<String, Object> map = new HashMap<>();
            map.put("status", status);
            map.put("oldValue", map1.get(key));
            map.put("newValue", map2.get(key));
            compareResult.put(key, map);
        }

        return compareResult;
    }
}
