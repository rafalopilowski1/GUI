package zad1;
public class Tekstowe {
    public static void main(String[]args) {
        TwoStringOper[] a = {
                new Concat(),new ConcatRev(),new Initials(),new Separ(" loves ")
        };
        for (TwoStringOper op : a) {
            System.out.println(op.apply("Mary", "John"));
        }
    }
}
interface TwoStringOper {
    String apply(String left, String right);
}
class Concat implements TwoStringOper {
    @Override
    public String apply(String left, String right) {
        return left.concat(right);
    }
}
class ConcatRev implements TwoStringOper {
    @Override
    public String apply(String left, String right) {
        return right.concat(left);
    }
}
class Initials implements TwoStringOper {
    @Override
    public String apply(String left, String right) {
        return String.valueOf(left.charAt(0)).concat(String.valueOf(right.charAt(0)));
    }
}
class Separ implements TwoStringOper {
    String seperator;
    public Separ(String seperator) {
        this.seperator = seperator;
    }
    @Override
    public String apply(String left, String right) {
        return left.concat(seperator).concat(right);
    }
}