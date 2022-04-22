package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Words implements Iterable<Entry<String, Integer>> {
    private final Map<String, Integer> wordsMap;

    public Words(String file) throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get(file)).flatMap((str) -> Arrays.stream(str.split("\\s")));
        wordsMap = Words.init(stringStream);
    }

    public static Map<String, Integer> init(Stream<String> splitted) {
        List<String> words = splitted
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
    public static void main(String[] args) throws IOException {
        String file = "2022-04-22/CountWords.txt";
        for (Map.Entry<String, Integer> e : new Words(file))
            System.out.println(
                    e.getKey() + " -> " + e.getValue());
    }
}
