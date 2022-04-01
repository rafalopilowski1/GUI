package zad2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class Generics {
    public static <T, R extends Comparable<R>> Comparator<T> compCmp(Comparator<T> compator, Function<T, R> function) {
        return (T o1, T o2) -> {
            if (compator.compare(o1, o2) != 0) {
                return compator.compare(o1, o2);
            } else {
                return function.apply(o1).compareTo(function.apply(o2));
            }
        };
    }

    public static void main(String[] args) {
        Person[] arr = {
                new Person("Eve", 43), new Person("Joe", 34),
                new Person("Kim", 30), new Person("Joe", 25),
                new Person("Kim", 20), new Person("Eve", 27),
        };
        Comparator<Person> cmp = compCmp(
                (p1, p2) -> p1.getName().compareTo(p2.getName()),
                Person::getAge);
        Arrays.sort(arr, cmp);
        System.out.println(Arrays.toString(arr));

    }
}

class Person {
    String name;
    int age;

    Person(String n, int a) {
        name = n;
        age = a;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        // just for test...
        return name + "(" + age + ")";
    }
}