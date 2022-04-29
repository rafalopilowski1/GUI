/*
  Tworzy pewną liczbę wątków przekazując do ich konstruktorów kolejne litery;
  każdy wątek wypisuje co pewien czas przypisaną sobie literę (na przykład można
  losować ten odstęp czasowy z przedziału [100, 1000] milisekund).
• Wszystkie wątki są startowane, ale pierwszy jest wstrzymany (suspended).
• Inny wątek, nazwijmy go głównym, co pewien czas (na przykład co 5 sekund)
  uruchamia wstrzymany wątek, a wstrzymuje następny — cyklicznie, tzn. jeśli
  uruchamiany jest ostatni, to wstrzymywany jest znowu pierwszy.
• Po kilku takich cyklach (na przykład dziesięciu), wątek główny zatrzymuje
  wszystkie wątki, które z kolei wypisują komunikat o końcu swojej pracy.
 */
package src;

import java.util.ArrayList;

class MyThread extends Thread {
    private final Character letter;

    private boolean locked;

    synchronized public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public MyThread(Character letter) {
        super();
        this.setName("MyThread " + letter + " " + this.getId());
        this.letter = letter;
        this.locked = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (locked) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.print(letter);
            try {
                Thread.sleep((long) (Math.random() * 900 + 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class MyThreadManager {
    private final ArrayList<MyThread> threads;
    private Integer currentSuspended;

    public MyThreadManager(String letters) {
        this.threads = new ArrayList<>();
        this.currentSuspended = 0;
        populateThreads(letters);
    }

    private void populateThreads(String letters) {
        for (var letter : letters.toCharArray()) threads.add(new MyThread(letter));
    }

    void start() throws InterruptedException {
        // Not starting first thread
        for (int j = 1; j < threads.size(); j++) {
            MyThread t = threads.get(j);
            System.out.printf("Starting %s!\n", t);
        }

        for (int i = 1; i < threads.size(); i++) {
            MyThread t = threads.get(i);
            t.start();
        }

        System.out.printf("Starting main loop with %s threads!\n", threads.size());
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            switchSuspense();
        }
        Thread.sleep(1000);
        System.out.println("\nClosing...");
        System.exit(0);
    }


    void switchSuspense() {
        var previousSuspended = threads.get(currentSuspended);
        currentSuspended = (currentSuspended + 1) % threads.size();
        var nextToSuspend = threads.get(currentSuspended);
        System.out.printf("\nWaking up %s and suspending %s\n", previousSuspended, nextToSuspend);
        if (previousSuspended.isAlive()) {
            previousSuspended.setLocked(false);
            synchronized (previousSuspended) {
                previousSuspended.notify();
            }
        } else
            previousSuspended.start();
        nextToSuspend.setLocked(true);
    }
}


public class Zad2 {
    public static void main(String[] args) throws InterruptedException {
        var manager = new MyThreadManager("ABCDŁĘ");
        manager.start();
    }
}