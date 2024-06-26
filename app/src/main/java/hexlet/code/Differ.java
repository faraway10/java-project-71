package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> map1 = Parser.parseFileToMap(filePath1);
        Map<String, String> map2 = Parser.parseFileToMap(filePath2);

        List<String> allKeys = new ArrayList<String>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        allKeys = allKeys.stream().distinct().sorted().toList();

        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");

        for (String key:allKeys) {
            boolean isEqual = Objects.equals(map1.get(key), map2.get(key));
            boolean isRemoved = map1.containsKey(key) && !map2.containsKey(key);
            boolean isAdded = !map1.containsKey(key) && map2.containsKey(key);
            boolean isChanged = !isEqual && !isRemoved && !isAdded;

            if (isEqual) {
                result.add("    " + key + ": " + map1.get(key));
            }

            if (isRemoved || isChanged) {
                result.add("  - " + key + ": " + map1.get(key));
            }

            if (isAdded || isChanged) {
                result.add("  + " + key + ": " + map2.get(key));
            }
        }

        return result.toString();
    }
}
