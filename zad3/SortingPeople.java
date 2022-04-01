public class SortingPeople {
    public static void main(String[] args) {
        
    }
}

/**
 * Person
 */
public class Person {
     
}

enum Sex {
    M, F
}
enum Size {
    XS, S, M, L, XL
}

enum Country {
    PL,NL,DE;

    @Override
    public String toString() {
        switch (this) {
            case DE -> "Deustchland";
            case NL -> "Nederlands";
            case PL -> "Poland";
        }
    }
}