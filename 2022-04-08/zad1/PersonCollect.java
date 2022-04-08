package zad1;

import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public static boolean isInColl(Collection<Person> coll, String name, int year) {
        return coll.contains(new Person(name, year));
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return birthYear == person.birthYear && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }

    @Override
    public int compareTo(Person o) {
        if (this.name.compareTo(o.name) == 0) return this.birthYear - o.birthYear;
        return this.name.compareTo(o.name);
    }
}

public class PersonCollect {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person("Alice", 2000), new Person("Brenda", 2001), new Person("Cecilia", 2002));
        System.out.println(Person.isInColl(list, "Brenda", 2001));
        System.out.println(Person.isInColl(list, "Debby", 2001));
        Set<Person> tSet = new TreeSet<>(list);
        System.out.println(Person.isInColl(tSet, "Brenda", 2001));
        System.out.println(Person.isInColl(tSet, "Debby", 2001));
        Set<Person> hSet = new HashSet<>(list);
        System.out.println(Person.isInColl(hSet, "Brenda", 2001));
        System.out.println(Person.isInColl(hSet, "Debby", 2001));
    }
}
