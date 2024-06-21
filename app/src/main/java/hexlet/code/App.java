package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.*;
import java.util.concurrent.Callable;

import java.nio.file.Paths;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String jsonStr1 = Files.readString(Paths.get(filepath1));
        String jsonStr2 = Files.readString(Paths.get(filepath2));

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

        System.out.println(result.toString());

        return 0;
    }
}
