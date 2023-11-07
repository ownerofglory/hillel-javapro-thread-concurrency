package ua.ithillel.translator;

import ua.ithillel.translator.client.TranslatorClient;
import ua.ithillel.translator.model.TranslationResult;

public class Receiver implements Runnable {
    private MessageQueue messageQueue;
    private TranslatorClient translatorClient;

    public Receiver(MessageQueue messageQueue, TranslatorClient translatorClient) {
        this.messageQueue = messageQueue;
        this.translatorClient = translatorClient;
    }

    @Override
    public void run() {
        synchronized (messageQueue) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!messageQueue.isEmpty()) {
                        final Message message = messageQueue.remove();

                        final TranslationResult translationResult = translatorClient.translateIntoPirate(message);
                        System.out.println("Translated message: " + translationResult.getContents());
                    } else {
                        messageQueue.wait();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
