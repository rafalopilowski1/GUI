package src;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Randomness {
    public static void main(String[] args) {
        Random r = new Random();
        final int N = 10_000_000, M = 10;
        Stream
                .generate(r::nextInt)
                .limit(N)
                .map(
                        (random) -> {
                            int div = random % M;
                            return Map.entry(Math.abs(div), random);
                        }
                )
                .collect(
                        Collectors.groupingBy(Map.Entry::getKey)
                )
                .forEach(
                            (group, students) -> {
                                float average = (float) (students.size()) / N;
                                System.out.printf("%s -> %s\n", group, average);
                            }
                );
    }
}
