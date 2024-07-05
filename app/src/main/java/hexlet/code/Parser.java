package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String rawString, String format) throws Exception {
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
