package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parseFileToMap(filePath1);
        Map<String, Object> map2 = Parser.parseFileToMap(filePath2);

        List<String> allKeys = new ArrayList<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        allKeys = allKeys.stream().distinct().sorted().toList();

        List<Map<String, Object>> rawCompareList = new ArrayList<>();

        for (String key:allKeys) {
            boolean isEqual = Objects.equals(map1.get(key), map2.get(key));
            boolean isRemoved = map1.containsKey(key) && !map2.containsKey(key);
            boolean isAdded = !map1.containsKey(key) && map2.containsKey(key);
            boolean isChanged = !isEqual && !isRemoved && !isAdded;
            String keyStatus = null;

            if (isEqual) {
                keyStatus = "equal";
            } else if (isChanged) {
                keyStatus = "changed";
            } else if (isRemoved) {
                keyStatus = "removed";
            } else {
                keyStatus = "added";
            }

            addToRawCompareList(rawCompareList, key, map1.get(key), map2.get(key), keyStatus);
        }

        return Formatter.format(rawCompareList, format);
    }

    public static void addToRawCompareList(List<Map<String, Object>> rawCompareList,
                                           String key, Object oldValue, Object newValue, String keyStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("oldValue", oldValue);
        map.put("newValue", newValue);
        map.put("keyStatus", keyStatus);
        rawCompareList.add(map);
    }
}
