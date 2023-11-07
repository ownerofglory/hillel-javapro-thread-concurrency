package ua.ithillel.translator;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<Message> queue = new LinkedList<>();

    public  void add(Message message) {
        queue.add(message);
    }

    public  Message remove() {
        return queue.remove();
    }

    public  boolean isEmpty() {
        return queue.isEmpty();
    }
}
