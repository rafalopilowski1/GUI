import java.util.*;

public class Prasa {
    /**
     * The Observer pattern is a software design pattern in which an object, called the subject, maintains a list of its
     * dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their
     * methods.
     */
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

/**
 * The PressAgency class has a list of subscribers, and each subscriber has a list of topics they are subscribed to. When
 * the PressAgency broadcasts a headline, it adds the headline to the list of headlines for each subscriber who is
 * subscribed to that topic
 */
class Person {
    static int count = 1;
    int id;
    String name;
    List<Topics> subscribedTopics;
    Map<Topics, List<String>> receivedNews;

    // The constructor for the Person class. It is called when a new Person object is created.
    public Person(String name) {
        id = count;
        this.name = name;
        count++;
        subscribedTopics = new ArrayList<>();
        receivedNews = new HashMap<>();
    }

    /**
     * The function iterates through the receivedNews map, and for each topic, it iterates through the list of news for
     * that topic, and prints the news
     *
     * @return A string representation of the object.
     */
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

    // The constructor for the PressAgency class. It is called when a new PressAgency object is created.
    PressAgency() {
        subscribers = new ArrayList<>();
    }

    /**
     * If the studentPerson is not already in the subscribers list, add them to the list. Then, add the topic to the
     * studentPerson's subscribedTopics list
     *
     * @param topic The topic that the student wants to subscribe to.
     * @param studentPerson The person object that is subscribing to the topic.
     */
    public void subscribe(Topics topic, Person studentPerson) {
        if (!subscribers.contains(studentPerson))
            subscribers.add(studentPerson);
        subscribers.get(subscribers.indexOf(studentPerson)).subscribedTopics.add(topic);
    }

    /**
     * If the person is a subscriber, and the person is subscribed to the topic, then remove the topic from the person's
     * subscribed topics
     *
     * @param topic The topic to unsubscribe from.
     * @param studentPerson The person who wants to unsubscribe from the topic.
     */
    public void unsubscribe(Topics topic, Person studentPerson) throws IllegalArgumentException {
        if (!subscribers.contains(studentPerson))
            throw new IllegalArgumentException("Person isn't an subscriber!");
        List<Topics> subscribersTopics = subscribers.get(subscribers.indexOf(studentPerson)).subscribedTopics;
        if (subscribersTopics.contains(topic))
            subscribersTopics.remove(topic);
        else
            throw new IllegalArgumentException(String.format("\"%s\" didn't subscribe to %s!", studentPerson.name, topic.toString()));
    }

    /**
     * For each person who is subscribed to the given topic, add the given headline to the list of headlines they've
     * received for that topic.
     *
     * @param topic the topic of the news
     * @param headline the headline of the news article
     */
    public void broadcast(Topics topic, String headline) {
        subscribers
                .stream()
                .filter((person) -> person
                        .subscribedTopics
                        .contains(topic)
                )
                .forEach((person) -> person
                        .receivedNews
                        .computeIfAbsent(topic, key -> new ArrayList<>())
                        .add(headline));
    }
}
