package zad2;

public class Spiewanie {
    public static void main(String[] args) {
        Singer s1 = new Singer("Martin"){
            @Override
            String sing() {
                return "Arrivederci, Roma...";
            }
        };
        Singer s2 = new Singer("Joplin"){
            @Override
            String sing() {
                return "...for me and my Bobby MacGee";
            }
        };
        Singer s3 = new Singer("Houston"){
            @Override
            String sing() {
                return "I will always love youuuu";
            }
        };
        Singer[] sng = {s1, s2, s3};
        for (Singer s : sng)
            System.out.println(s);

        System.out.println("\n" + Singer.loudest(sng));
    }
}
abstract class Singer {
    static int count;
    String surname;
    int number;
    public Singer(String surname) {
        count++;
        this.surname = surname;
        this.number = count;
    }
    abstract String sing();
    public String toString() {
        return String.format("""
                (%s) %s: %s
                """,this.number,this.surname, this.sing());
    }

    static String loudest(Singer[] table) {
        String max = "";
        int index = 0;
        for (int i = 0; i<table.length;i++) {
            String check = table[i].sing();
            if (max.length() < check.length()) {
                max = check;
                index = i;
            }
        }
        return table[index].toString();
    }
}