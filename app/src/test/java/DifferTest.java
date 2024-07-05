import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String resultStylish;

    private static String resultPlain;

    private static String resultJson;

    private static String resultStylishEmptyValues;

    private static String resultStylishEmptyFiles;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        resultStylishEmptyValues = readFixture("result_stylish_empty_values.txt");
        resultStylishEmptyFiles = readFixture("result_stylish_empty_files.txt");
    }

    @Test
    public void testGenerateJsonInDefaultOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testGenerateJsonInStylishOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "stylish");
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testGenerateJsonInPlainOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    public void testGenerateJsonInJsonOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "json");

        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> expectedParsed = mapper.readValue(resultJson, new TypeReference<Map<Object, Object>>() { });
        Map<Object, Object> actualParsed = mapper.readValue(actual, new TypeReference<Map<Object, Object>>() { });

        assertEquals(expectedParsed, actualParsed);
    }

    @Test
    public void testGenerateYmlInDefaultOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml");
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testGenerateYmlInStylishOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml", "stylish");
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testGenerateYmlInPlainOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    public void testGenerateYmlInJsonOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml", "json");

        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> expectedParsed = mapper.readValue(resultJson, new TypeReference<Map<Object, Object>>() { });
        Map<Object, Object> actualParsed = mapper.readValue(actual, new TypeReference<Map<Object, Object>>() { });

        assertEquals(expectedParsed, actualParsed);
    }

    @Test
    public void testGenerateJsonEmptyValuesInDefaultOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json");
        assertEquals(resultStylishEmptyValues, actual);
    }

    @Test
    public void testGenerateJsonEmptyInDefaultOut() throws Exception {
        String actual = Differ.generate("src/test/resources/file5.json", "src/test/resources/file6.json", "stylish");
        assertEquals(resultStylishEmptyFiles, actual);
    }
}
