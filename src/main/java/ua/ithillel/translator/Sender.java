package ua.ithillel.translator;

public class Sender {
    private final MessageQueue messageQueue;

    public Sender(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void send(Message message) {
        // send a message
        synchronized (messageQueue) {
            try {
                Thread.sleep(10);
                messageQueue.add(message);

                messageQueue.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
