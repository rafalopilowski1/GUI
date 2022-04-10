import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class Assign2 {

    static <T, R extends Comparable<R>> R minPredFun(T[] arr, Predicate<T> pred, Function<T, R> func) {
        return Arrays.stream(arr).filter(pred).map(func).min(R::compareTo).orElse(null);
    }

    public static void main(String[] args) {
        Person[] persons = {
                new Person("Adam", 16),
                new Person("Zoe", 19),
                new Person("Alice", 32),
                new Person("Kate", 23),
                new Person("Rachel", 12),
                new Person("Joe", 21)
        };
        String m1 = minPredFun(persons, x -> x.getAge() >= 18, Person::getName);
        System.out.println(m1);
        Integer m2 = minPredFun(persons, x -> x.getName().charAt(0) == 'A', Person::getAge);
        System.out.println(m2);
        String m3 = minPredFun(persons, x -> x.getName().length() >= 7, Person::getName);
        System.out.println(m3);
    }
}


class Person {
    String name;
    int age;

    Person(String n, int a) {
        name = n;
        age = a;
    }

    Integer getAge() {
        return age;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}