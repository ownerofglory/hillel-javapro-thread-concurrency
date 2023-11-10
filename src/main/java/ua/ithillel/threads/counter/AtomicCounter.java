package ua.ithillel.threads.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public int incrementAndGet() {
        // count++ - postfix
        // ++count - prefix
        return count.incrementAndGet();
    }
}
