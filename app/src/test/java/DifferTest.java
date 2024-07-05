import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
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

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertEquals(Differ.generate(filePath1, filePath2), resultStylish);
        assertEquals(Differ.generate(filePath1, filePath2, "stylish"), resultStylish);
        assertEquals(Differ.generate(filePath1, filePath2, "plain"), resultPlain);

        String actual = Differ.generate(filePath1, filePath2, "json");
        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> expectedParsed = mapper.readValue(resultJson, new TypeReference<Map<Object, Object>>() { });
        Map<Object, Object> actualParsed = mapper.readValue(actual, new TypeReference<Map<Object, Object>>() { });
        assertEquals(actualParsed, expectedParsed);
    }

    @Test
    public void testGenerateJsonEmptyValuesInDefaultOut() throws Exception {
        String filePath1 = getFixturePath("file3.json").toString();
        String filePath2 = getFixturePath("file4.json").toString();

        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(actual, resultStylishEmptyValues);
    }

    @Test
    public void testGenerateJsonEmptyInDefaultOut() throws Exception {
        String filePath1 = getFixturePath("file5.json").toString();
        String filePath2 = getFixturePath("file6.json").toString();

        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(actual, resultStylishEmptyFiles);
    }
}
