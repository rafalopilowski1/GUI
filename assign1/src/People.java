package src;

import java.util.Arrays;

public class People {
    public static void main(String[] args) {
        Student[] studs = {
                new Student("Poland", "Jan", "Kowalski"),
                new Student("France", "Jean", "Killy"),
                new Student("Poland", "Maria", "Nowak"),
                new Student("Poland", "Julia", "Nowak"),
                new Student("Germany", "Hans", "Schultz"),
                new Student("France", "Jeanne", "Marat"),
        };
        Arrays.sort(studs);
        System.out.println("Students: by lastname, firstname, country");
        for (Student s : studs)
            System.out.println(s);

        Citizen[] cits = {
                new Citizen("Poland", "Jan", "Kowalski"),
                new Citizen("France", "Jean", "Killy"),
                new Citizen("Poland", "Maria", "Nowak"),
                new Citizen("Poland", "Julia", "Nowak"),
                new Citizen("Germany", "Hans", "Schultz"),
                new Citizen("France", "Jeanne", "Marat"),
        };
        Arrays.sort(cits);
        System.out.println("Citizens: by country, lastname, firstname");
        for (Citizen c : cits)
            System.out.println(c);
    }
}

enum PersonType {
    Student,
    Citizen
}

class Person {
    protected String firstname;
    protected String lastname;
    protected String country;

    protected Person(String country, String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
    }
    protected static int check_values(Person first, Person o, PersonType type) {
        int firstname_check = first.firstname.compareTo(o.firstname);
        int lastname_check = first.lastname.compareTo(o.lastname);
        int country_check = first.country.compareTo(o.country);
        return switch (type) {
            case Student -> {
                if (lastname_check != 0) {
                    yield lastname_check;
                } else if (firstname_check != 0) {
                    yield firstname_check;
                } else yield country_check;
            }
            case Citizen -> {
                if (country_check != 0) {
                    yield country_check;
                } else if (lastname_check != 0) {
                    yield lastname_check;
                } else yield firstname_check;
            }
        };
    }
    @Override
    public String toString() {
        return String.format("%s %s (%s)", firstname, lastname, country);
    }
}

class Student extends Person implements Comparable<Student> {
    protected Student(String country, String firstname, String lastname) {
        super(country, firstname, lastname);
    }
    @Override
    public int compareTo(Student o) {
        return Person.check_values(this, o, PersonType.Student);
    }
}

class Citizen extends Person implements Comparable<Citizen> {
    protected Citizen(String country, String firstname, String lastname) {
        super(country, firstname, lastname);
    }
    @Override
    public int compareTo(Citizen o) {
        return Person.check_values(this, o, PersonType.Citizen);
    }
}