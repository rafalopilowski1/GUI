package src;

import java.util.*;

public class Prasa {
    public static void main(String[] args) {
        Person john = new Person("John");
        Person kate = new Person("Kate");
        Person bill = new Person("Bill");

        PressAgency agency = new PressAgency();

        agency.subscribe(Topics.POLITICS, john);
        agency.subscribe(Topics.SPORT, john);
        agency.subscribe(Topics.POLITICS, kate);
        agency.subscribe(Topics.FASHION, kate);
        agency.subscribe(Topics.CELEBRITIES, bill);
        agency.subscribe(Topics.SPORT, bill);

        agency.broadcast(Topics.POLITICS, "Trump's speech in Washington");
        agency.broadcast(Topics.FASHION, "Skirts get shorter this season!");
        agency.broadcast(Topics.SPORT, "Real-Atletico 2:1");
        agency.broadcast(Topics.POLITICS, "British-French summit in Paris");
        agency.broadcast(Topics.SPORT, "Federer wins in Australia");
        agency.broadcast(Topics.FASHION, "New handbags from Versace!");

        agency.unsubscribe(Topics.POLITICS, john);

        agency.broadcast(Topics.POLITICS, "Biden's speech in Washington");

        System.out.println(john);
        System.out.println(kate);
        System.out.println(bill);
    }
}

enum Topics {
    CELEBRITIES,
    FASHION,
    POLITICS,
    SPORT
}

class Person {
    static int count = 1;
    int id;
    String name;
    List<Topics> subscribedTopics;
    Map<Topics, List<String>> receivedNews;

    public Person(String name) {
        id = count;
        this.name = name;
        count++;
        subscribedTopics = new ArrayList<>();
        receivedNews = new HashMap<>();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (id=%s)\n", name, id));
        receivedNews.forEach((topic, newsList) -> {
            sb.append(String.format("   %s: %s news\n", topic.toString(), newsList.size()));
            newsList.forEach((news) -> sb.append(String.format("      %s\n", news)));
        });
        subscribedTopics.forEach((topic -> {
            if (!receivedNews.containsKey(topic))
                sb.append(String.format("   %s: %s news\n", topic.toString(), "no"));
        }));
        return sb.toString();
    }
}

class PressAgency {
    List<Person> subscribers;
    PressAgency() {
        subscribers = new ArrayList<>();
    }
    public void subscribe(Topics topic, Person studentPerson) {
        if (!subscribers.contains(studentPerson))
            subscribers.add(studentPerson);
        subscribers.get(subscribers.indexOf(studentPerson)).subscribedTopics.add(topic);
    }
    public void unsubscribe(Topics topic, Person studentPerson) {
        if (!subscribers.contains(studentPerson))
            throw new IllegalArgumentException("Person isn't an subscriber!");
        subscribers.get(subscribers.indexOf(studentPerson)).subscribedTopics.remove(topic);
    }
    public void broadcast(Topics topic, String headline) {
        subscribers
                .stream()
                .filter((person) -> person
                        .subscribedTopics
                        .contains(topic)
                )
                .forEach((person) -> person
                        .receivedNews
                        .computeIfAbsent(topic, k -> new ArrayList<>())
                        .add(headline));
    }
}
