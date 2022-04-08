package zad2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

class Car implements Comparable<Car> {
    String marka;
    int cena;

    public Car(String marka, int cena) {
        this.marka = marka;
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka,cena);
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car) obj;
        return marka.equals(car.marka) && cena == car.cena;
    }

    @Override
    public String toString() {
        return String.format("%s %s",marka,cena);
    }

    @Override
    public int compareTo(Car o) {
        if (o.marka.equals(marka)) return o.cena - cena;
        return o.marka.compareTo(marka);
    }
}


public class Samochody {
    public static void main(String[] args) {
        String[] arr = {
                "salon A", "Mercedes", "130000",
                "salon B", "Mercedes", "120000",
                "salon C", "Ford", "110000",
                "salon B", "Opel", "90000",
                "salon C", "Honda", "95000",
                "salon A", "Ford", "105000",
                "salon A", "Renault", "75000"
        };
        LinkedHashMap<String, List<Car>> map = new LinkedHashMap<>();
        for (int i = 0; i < (arr.length - arr.length % 3); i+=3) {
            if (!map.containsKey(arr[i])) {
                ArrayList<Car> list = new ArrayList<>();
                list.add(new Car(arr[i+1],Integer.parseInt(arr[i+2])));
                map.put(arr[i],list);
            } else {
                List<Car> list = map.get(arr[i]);
                list.add(new Car(arr[i + 1], Integer.parseInt(arr[i + 2])));
                map.put(arr[i], list);
            }
        }
        System.out.println(map);
        Car minCar = map.entrySet().stream().flatMap(el -> el.getValue().stream()).min(Car::compareTo).get();
        String minKey = "";
        for (String key:
             map.keySet()) {
            if (map.get(key).contains(minCar)) {
                minKey = key;
                break;
            }
        }
        System.out.println(String.format("%s in %s for %s",minCar.marka, minKey,minCar.cena));
    }
}
