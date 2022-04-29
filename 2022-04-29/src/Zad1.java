package src;

import java.util.ArrayList;
import java.util.Iterator;

class Letters implements Iterable<Thread> {
    public ArrayList<Thread> threadArrayList;

    public Letters(String letters) {
        this.threadArrayList = new ArrayList<>();
        for (char el :
                letters.toCharArray()) {
            Thread letter_thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print(el);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            letter_thread.setName("Thread " + el);
            threadArrayList.add(letter_thread);
        }
    }

    @Override
    public Iterator<Thread> iterator() {
        return threadArrayList.iterator();
    }

    public void start() {
        for (Thread thread : threadArrayList) thread.start();
    }

    public void stop() {
        for (Thread thread : threadArrayList) thread.interrupt();
    }
}


public class Zad1 {
    public static void main(String[] args) {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters) System.out.println(t.getName() + " starting");
        letters.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return;
        }
        letters.stop();
        System.out.println("\nProgram completed.");
    }
}
