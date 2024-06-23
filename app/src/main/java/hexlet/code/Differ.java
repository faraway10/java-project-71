package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String jsonStr1 = Files.readString(Paths.get(filePath1));
        String jsonStr2 = Files.readString(Paths.get(filePath2));

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map1 = objectMapper.readValue(jsonStr1, new TypeReference<Map<String, String>>(){});
        Map<String, String> map2 = objectMapper.readValue(jsonStr2, new TypeReference<Map<String, String>>(){});

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
