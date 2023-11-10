package ua.ithillel.threads.counter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexCounter {
    private int count;
    private Lock lock = new ReentrantLock(true);

    public int incrementAndGet() {
        // count++ - postfix
        // ++count - prefix
        try {
//            boolean locked;
//            while (!(locked = lock.tryLock())) {
//                System.out.println("Do smth else");
//            }


            lock.lock();

            ++count;


        } finally {
            lock.unlock();

        }

        return count;
    }

    public int get() {
        return count;
    }
}
