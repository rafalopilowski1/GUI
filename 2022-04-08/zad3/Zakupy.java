package zad3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

class Purchase {
    private String name;
    private int price;

    public Purchase(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

public class Zakupy {
    public static void main(String[] args) {
        TreeMap<String, List<Purchase>> map = new TreeMap<>();
        try (FileInputStream fis = new FileInputStream("2022-04-08/zad3/input.txt")) {
            Scanner scan = new Scanner(fis);
            while (scan.hasNextLine()) {
                String[] dataLine = scan.nextLine().split(" ");
                String key = dataLine[0];
                Purchase purchase = new Purchase(dataLine[1],Integer.parseInt(dataLine[2]));
                List<Purchase> value;
                if (!map.containsKey(key)) {
                    value = new ArrayList<>();
                } else {
                    value = map.get(key);
                }
                value.add(purchase);
                map.put(key,value);
            }
            scan.close();
            FileOutputStream fos = new FileOutputStream("2022-04-08/zad3/output.txt");
            ArrayList<String> list = new ArrayList<>();
            map.forEach((key, value) -> {
                int numberOfPurchases = value.size();
                long numberOfUniquePurchases = value.stream().map(Purchase::getName).distinct().count();
                int totalPrice = value.stream().map(Purchase::getPrice).reduce(0,Integer::sum);
                list.add(String.format("%s %s %s %s\n",key,numberOfPurchases,numberOfUniquePurchases,totalPrice));
            });
            list.forEach(line -> {
                try {
                    fos.write(line.getBytes(StandardCharsets.UTF_8));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });
            fos.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
