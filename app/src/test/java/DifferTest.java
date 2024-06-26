import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerate1() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerate2() throws Exception {
        String expected = "{\n"
                + "    host: \n"
                + "  - proxy: \n"
                + "  + verbose: \n"
                + "}";
        String actual = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerate3() throws Exception {
        String expected = "{\n\n}";
        String actual = Differ.generate("src/test/resources/file5.json", "src/test/resources/file6.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerate4() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate("src/test/resources/file7.yml", "src/test/resources/file8.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerate5() throws Exception {
        String expected = "{\n"
                + "    host: \n"
                + "  - proxy: \n"
                + "  + verbose: \n"
                + "}";
        String actual = Differ.generate("src/test/resources/file9.yml", "src/test/resources/file10.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerate6() throws Exception {
        String expected = "{\n\n}";
        String actual = Differ.generate("src/test/resources/file11.yml", "src/test/resources/file12.yml");
        assertEquals(expected, actual);
    }
}
