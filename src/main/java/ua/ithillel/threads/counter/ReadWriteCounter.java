package ua.ithillel.threads.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    private int count;
    private Lock readLock;
    private Lock writeLock;

    public ReadWriteCounter() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public int incrementAndGet() {
        // count++ - postfix
        // ++count - prefix
        try {
//            boolean locked;
//            while (!(locked = lock.tryLock())) {
//                System.out.println("Do smth else");
//            }


            writeLock.lock();

            ++count;


        } finally {
            writeLock.unlock();

        }

        return count;
    }

    public int get() {
        try {
            readLock.lock();

        } finally {
            readLock.unlock();
        }

        return count;
    }
}
