package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseFileToMap(String filePath) throws Exception {
        String format = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();

        String rawString = Files.readString(Paths.get(filePath));

        switch (format) {
            case "json":
                ObjectMapper jsonMapper = new ObjectMapper();
                return jsonMapper.readValue(rawString, new TypeReference<Map<String, Object>>() { });
            case "yml":
                ObjectMapper yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(rawString, new TypeReference<Map<String, Object>>() { });
            default:
                throw new RuntimeException("\nUnsupported file format: " + format);
        }
    }
}
