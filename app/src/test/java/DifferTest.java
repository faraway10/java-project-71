import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private final String expected1 = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";

    private final String expected2 = "{\n"
            + "    host: \n"
            + "  - proxy: \n"
            + "  + verbose: \n"
            + "}";

    private final String expected3 = "{\n\n}";

    private final String expected4 = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
    @Test
    public void testGenerate1() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "stylish");
        assertEquals(expected1, actual);
    }

    @Test
    public void testGenerate2() throws Exception {
        String actual = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json", "stylish");
        assertEquals(expected2, actual);
    }

    @Test
    public void testGenerate3() throws Exception {
        String actual = Differ.generate("src/test/resources/file5.json", "src/test/resources/file6.json", "stylish");
        assertEquals(expected3, actual);
    }

    @Test
    public void testGenerate4() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml", "stylish");
        assertEquals(expected1, actual);
    }

    @Test
    public void testGenerate5() throws Exception {
        String actual = Differ.generate("src/test/resources/file9.yml", "src/test/resources/file10.yml", "stylish");
        assertEquals(expected2, actual);
    }

    @Test
    public void testGenerate6() throws Exception {
        String actual = Differ.generate("src/test/resources/file11.yml", "src/test/resources/file12.yml", "stylish");
        assertEquals(expected3, actual);
    }

    @Test
    public void testGenerate7() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "plain");
        assertEquals(expected4, actual);
    }

    @Test
    public void testGenerate8() throws Exception {
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml", "plain");
        assertEquals(expected4, actual);
    }
}
