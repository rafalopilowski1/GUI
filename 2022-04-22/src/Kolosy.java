package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

class StudentKolos {
    private String name;
    private String group;
    private int score;

    public StudentKolos(String name, String group, int score) {
        this.name = name;
        this.group = group;
        this.score = score;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)-%s",name,group,score);
    }
}

public class Kolosy {
    public static void main(String[] args) throws IOException {
        Files
                .lines(
                    Paths.get("2022-04-22/scores.txt")
                )
                .map((line) -> {
                    String[] details = line.split("\\s");
                    return new StudentKolos(details[0], details[1], Integer.parseInt(details[2]));
                })
                .collect(
                        Collectors.groupingBy(StudentKolos::getGroup)
                )
                .forEach((group, students) -> System.out.printf("Group %s: %s%n", group, Arrays.toString(students.toArray())));
    }
}
