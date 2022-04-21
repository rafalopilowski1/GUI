import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Words implements Iterable<Entry<String, Integer>> {
    private final Map<String, Integer> wordsMap;

    public Words(String file) {
        byte[] arr = {};
        try {
            FileInputStream fis = new FileInputStream(file);
            arr = fis.readAllBytes();
            fis.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String text = new String(arr);
        String[] spitted = text.split("\\s");
        wordsMap = Words.init(spitted);
    }

    public static Map<String, Integer> init(String[] splitted) {
        List<String> words = Arrays.stream(splitted)
                .map(
                        (word) -> word
                                .chars()
                                .filter(Character::isAlphabetic)
                                .collect(
                                        StringBuilder::new,
                                        StringBuilder::appendCodePoint,
                                        StringBuilder::append
                                ).toString()
                )
                .filter((word) -> !word.isEmpty())
                .toList();
        return words
                .stream()
                .collect(Collectors.groupingBy(String::toLowerCase))
                .entrySet()
                .stream()
                .map(
                        (entry) -> Map.entry(
                                entry.getKey(),
                                entry.getValue().size()
                        )
                )
                .collect(
                        Collectors.toMap(
                                Entry::getKey,
                                Entry::getValue
                        )
                );
    }

    @Override
    public Iterator<Entry<String, Integer>> iterator() {
        return wordsMap.entrySet().iterator();
    }
}

public class Slowa {
    public static void main(String[] args) {
        String file = "CountWords.txt";
        for (Map.Entry<String, Integer> e : new Words(file))
            System.out.println(
                    e.getKey() + " -> " + e.getValue());
    }
}
