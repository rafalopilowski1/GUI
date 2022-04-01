package zad3;

import java.util.Arrays;
import java.util.Comparator;

public class SortingPeople {
    static <T> void printArray(String title, T[] array) {
        System.out.println("   *** " + title + " ***   ");
        for (T t : array) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        Person[] persons = {
                new Person("Max", Sex.M, Size.XL, Country.NL),
                new Person("Jan", Sex.M, Size.S, Country.PL),
                new Person("Eva", Sex.F, Size.XS, Country.NL),
                new Person("Lina", Sex.F, Size.L, Country.DE),
                new Person("Mila", Sex.F, Size.S, Country.DE),
                new Person("Ola", Sex.F, Size.M, Country.PL),
        };
        Comparator<Person> sexThenSize = (Person o1, Person o2) -> {
            if (o1.sex.compareTo(o2.sex) != 0)
                return o1.sex.compareTo(o2.sex);
            else
                return o1.size.compareTo(o2.size);
        };

        Arrays.sort(persons, sexThenSize);
        printArray("Persons by sex and then size", persons);

        Arrays.sort(persons, (Person o1, Person o2) -> {
            if (o1.size.compareTo(o2.size) != 0)
                return o1.size.compareTo(o2.size);
            else
                return o1.name.compareTo(o2.name);
        });
        printArray("Persons by size and then name", persons);

        Country[] countries = Country.values();
        Arrays.sort(countries, (Country o1, Country o2) -> {
            return o1.compareTo(o2);
        });
        printArray("Countries by name", countries);
    }
}

/**
 * Person
 */
class Person {
    protected Sex sex;
    protected Size size;
    protected Country country;
    protected String name;

    public Person(String string, Sex sex, Size size, Country country) {
        this.sex = sex;
        this.size = size;
        this.country = country;
        this.name = string;
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s, %s)", name, sex, size, country);
    }
}

enum Sex {
    F, M
}

enum Size {
    XS, S, M, L, XL
}

enum Country {
    PL, NL, DE;

    @Override
    public String toString() {
        return switch (this) {
            case DE -> "Germany";
            case NL -> "Netherlands";
            case PL -> "Poland";
        };
    }
}