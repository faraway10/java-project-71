package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String format1 = filePath1.substring(filePath1.lastIndexOf(".") + 1).toLowerCase();
        String format2 = filePath2.substring(filePath2.lastIndexOf(".") + 1).toLowerCase();

        String rawString1 = Files.readString(Paths.get(filePath1));
        String rawString2 = Files.readString(Paths.get(filePath2));

        Map<String, Object> map1 = Parser.parse(rawString1, format1);
        Map<String, Object> map2 = Parser.parse(rawString2, format2);

        Map<String, Map<String, Object>> compareResult = Comparator.compare(map1, map2);

        return Formatter.format(compareResult, format);
    }
}
