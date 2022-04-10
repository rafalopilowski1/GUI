package zad3;

public class Odwracanie {
    public static void main(String[] args) {
        Reversible[] revers = new Reversible[] {
                new ReversibleString("Cat"),
                new ReversibleDouble(2),
                new ReversibleDouble(3),
                new ReversibleString("Dog"),
                new ReversibleString("Alice in Wonderland"),
                new ReversibleDouble(10),
        };

        System.out.println("Original:");
        for (Reversible r : revers)
            System.out.println(r);

        for (Reversible r : revers) {
            r.reverse();
        }
        System.out.println("Reversed:");
        for (Reversible r : revers)
            System.out.println(r);

        System.out.println("Reversed again and modified:");
        for (Reversible r : revers) {
            switch (r.getClass().getSimpleName()) {
                // `instanceof` !!!
                 case "ReversibleString" -> {
                     System.out.println("Text: " + r.reverse());
                 }
                 case "ReversibleDouble" -> {
                     ReversibleDouble doubles = (ReversibleDouble) (r.reverse());
                     doubles.data += 10.;
                     System.out.println(doubles);
                 }
            }
        }
    }
}

interface Reversible {
    Reversible reverse();
}

class ReversibleString implements Reversible {
    String data;
    public ReversibleString(String data) {
        this.data = data;
    }
    @Override
    public Reversible reverse() {
        StringBuilder sb = new StringBuilder();
        char[] array = data.toCharArray();
        for (int i = array.length-1; i >= 0;i--) {
            sb.append(array[i]);
        }
        this.data = sb.toString();
        return this;
    }
    @Override
    public String toString() {
        return data;
    }
}

class ReversibleDouble implements Reversible {
    double data;
    public ReversibleDouble(double data) {
        this.data = data;
    }
    @Override
    public Reversible reverse() {
        double value = 1. / data;
        this.data = value;
        return this;
    }
    @Override
    public String toString() {
        return Double.toString(data);
    }
}

