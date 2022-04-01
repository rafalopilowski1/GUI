package zad3;

import java.util.ArrayList;
import java.util.Arrays;


public class ArrayFilter {
    public static void main(String[] args) {
        String[] arr = {"Alice","Sue","Janet","Bea"};
        System.out.println(Arrays.toString(arr));

        LenFilter lenFilter = new LenFilter(3);
        String[] a1 = SFilter.filter(arr,lenFilter);
        System.out.println(Arrays.toString(a1));

        String[] a2 = SFilter.filter(arr, new SFilter() {
            @Override
            public boolean test(String text) {
                char first = text.charAt(0);
                return (first < 68 && first >= 65);
            }
        });
        System.out.println(Arrays.toString(a2));
        String[] a3 = SFilter.filter(arr, (text) -> {
            char first = text.charAt(0);
            return (first > 72 && first <= 90);
        });
        System.out.println(Arrays.toString(a3));

    }
}
interface SFilter {
    boolean test(String text);
    static String[] filter(String[] arr, SFilter filt) {
        ArrayList<String> list = new ArrayList<>();
        for (String text:
             arr) {
            if (filt.test(text)) {
                list.add(text);
            }
        }
        return list.toArray(new String[0]);
    }
}

class LenFilter implements SFilter {
    int minLength;

    public LenFilter(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean test(String text) {
        return text.length() > minLength;
    }
}
