package zad1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kolory {
    public static void main(String[] args) {
        List<MyColor> list = Arrays.asList(
                new MyColor(  1,  2,  3),
                new MyColor(255,  0,  0),
                new MyColor( 55, 55,100),
                new MyColor( 10,255, 10)
        );
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list, new MyColorCompar(ColComponent.RED));
        System.out.println(list);
        Collections.sort(list, new MyColorCompar(ColComponent.GREEN));
        System.out.println(list);
        Collections.sort(list, new MyColorCompar(ColComponent.BLUE));
        System.out.println(list);
    }
}

class MyColor extends java.awt.Color implements Comparable<MyColor> {

    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }
    @Override
    public String toString() {
        return String.format("(%d,%d,%d)",this.getRed(),this.getGreen(),this.getBlue());
    }

    @Override
    public int compareTo(MyColor o) {
        return this.getRGB() - o.getRGB();
    }
}
enum ColComponent {
    RED,
    GREEN,
    BLUE,
}

class MyColorCompar implements Comparator<MyColor> {
    ColComponent col;
    MyColorCompar(ColComponent col) {
        this.col = col;
    }
    @Override
    public int compare(MyColor o1, MyColor o2) {
        int answer;
        switch (col) {
            case RED -> {
                answer = o1.getRed() - o2.getRed();
            }
            case GREEN -> {
                answer = o1.getGreen() - o2.getGreen();
            }
            case BLUE -> {
                answer = o1.getBlue() - o2.getBlue();
            }
            default -> {
                answer = o1.getRGB() - o2.getRGB();
            }
        }
        return answer;
    }
}

