package src;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%s-%s",name,score);
    }
}

public class Kolosy {
    public static void main(String[] args) {
        byte[] array = {};
        try {
            FileInputStream fis = new FileInputStream("scores.txt");
            array = fis.readAllBytes();
            fis.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        String text = new String(array);
        String[] lines = text.split("\\n");
        Map<String, List<StudentKolos>> studentMap = Arrays.stream(lines).map((line) -> {
            String[] details = line.split("\\s");
            return new StudentKolos(details[0],details[1],Integer.parseInt(details[2]));
        }).collect(Collectors.groupingBy(StudentKolos::getGroup));
        studentMap.forEach((group, students) -> System.out.printf("Group %s: %s%n",group, Arrays.toString(students.toArray())));
    }
}
