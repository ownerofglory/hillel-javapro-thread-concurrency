package ua.ithillel.threads.counter;

public class Counter {
    private int count;

    public int incrementAndGet() {
        // count++ - postfix
        // ++count - prefix
        System.out.println("Incrementing and getting");

        synchronized (this) {
            incrementCount();
            return count;
        }
    }

    private synchronized void incrementCount() {
        count++;
    }
}
