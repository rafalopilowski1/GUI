package zad2.src;

import java.util.Arrays;

public class Wiek {
    public static void main(String[] args) {
        Person[] array = {new Person("Kadabra",16), new Person("Hokus",18),new Person("Pokus",20),new Person("Abra",14)};
        System.out.println(Arrays.toString(array));
        Person.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
class Person implements Comparable<Person> {
    private String name;
    private int age;

    Person(String name, int age) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }
    @Override
    public String toString() {
        return name + ": " + age;
    }
    public static void sort(Person[] personArray) {
        int last_index = personArray.length-1;
        while (last_index > 0) {
            for (int i = 0; i < last_index; i++) {
                for (int j = i; j < personArray.length-1; j++) {
                    int check = personArray[j].compareTo(personArray[j+1]);
                    if (check > 0) {
                        swap(personArray,j,j+1);
                    }
                }
            }
            last_index--;
        }
    }
    static void swap(Person[] array, int source, int destination) {
        Person tmp = array[destination];
        array[destination] = array[source];
        array[source]=tmp;
    }
}