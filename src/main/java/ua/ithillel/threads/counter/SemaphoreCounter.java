package ua.ithillel.threads.counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreCounter {
    private int count;
    private Semaphore semaphore = new Semaphore(1);

    public int incrementAndGet() {
        try {
            semaphore.acquire();
//            semaphore.tryAcquire(1, TimeUnit.SECONDS)


            ++count;


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }

        return count;
    }

    public int get() {
        return count;
    }
}
