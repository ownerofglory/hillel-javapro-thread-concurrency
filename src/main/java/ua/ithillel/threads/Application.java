package ua.ithillel.threads;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.io.server.MultiThrededServer;
import ua.ithillel.threads.counter.Counter;
import ua.ithillel.threads.mult.MyRunnable;
import ua.ithillel.threads.mult.MyThread;
import ua.ithillel.threads.shape.Circle;
import ua.ithillel.threads.shape.Shape;
import ua.ithillel.threads.shape.Square;
import ua.ithillel.translator.Message;
import ua.ithillel.translator.MessageQueue;
import ua.ithillel.translator.Receiver;
import ua.ithillel.translator.Sender;
import ua.ithillel.translator.client.FunnyTranslationClient;
import ua.ithillel.translator.client.TranslatorClient;

import java.io.*;
import java.net.http.HttpClient;
import java.util.Scanner;
import java.util.function.Function;

public class Application {
    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();
        Sender sender = new Sender(queue);


        TranslatorClient client = new FunnyTranslationClient(
                HttpClient.newHttpClient(),
                new ObjectMapper());
        Receiver receiver = new Receiver(queue, client);

        new Thread(receiver).start();
        new Thread(receiver).start();
        new Thread(receiver).start();


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            final String text = scanner.nextLine();
            final Message message = new Message(text);

            sender.send(message);
        }


        System.out.println("Application starts...");

        try(MultiThrededServer server = new MultiThrededServer(8080)) {
            server.start();


            final Counter counter = new Counter(); // 200k + 200k = 400k

            Runnable r1 = () -> {
                for (int i = 0; i < 200_000; i++) {
                    System.out.println("T1: " + counter.incrementAndGet());
                }
            };

            Runnable r2 = () -> {
                for (int i = 0; i < 200_000; i++) {
                    System.out.println("T2: " + counter.incrementAndGet());
                }
            };

//            StringBuffer buf1 = new StringBuffer();
//            StringBuffer buf2 = new StringBuffer();
//
//            Runnable r1 = () -> {
//                try (InputStream in =  Application.class
//                        .getClassLoader()
//                        .getResourceAsStream("file1.txt");
//
//                     Reader rd = new InputStreamReader(in);
//                     BufferedReader br = new BufferedReader(rd);
//                ) {
//
//                    br.lines().forEach(line -> buf1.append(line + "\n"));
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//
//            Runnable r2 = () -> {
//                try (InputStream in =  Application.class
//                        .getClassLoader()
//                        .getResourceAsStream("file2.txt");
//
//                     Reader rd = new InputStreamReader(in);
//                     BufferedReader br = new BufferedReader(rd);
//                ) {
//
//                    br.lines().forEach(line -> buf2.append(line + "\n"));
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            };

            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);

            t1.start();
            t2.start();


            t1.join();
            t2.join();

//            System.out.println(buf1.append(buf2));





//            Runnable r1 = () -> {
//                try {
//                    for (int i = 0; i < 1000; i++) {
//                        if (Thread.currentThread().isInterrupted()) {
//                            System.out.println("Interrupting T1");
//                            return;
//                        }
//                        System.out.println("T1" + ": " + i);
//                        Thread.sleep(100);
//                    }
//                } catch (InterruptedException e) {
//                    System.out.println("T1 has been interrupted");
//                }
//            };
//
//            Runnable r2 = () -> {
//                for (int i = 0; i < 1000; i++) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("Interrupting T2");
//                        return;
//                    }
//                    System.out.println("T2" + ": " + i);
//                }
//            };

//            Thread t1 = new Thread(r1);
//            Thread t2 = new Thread(r2);
//
//            t1.start();
//            t2.start();
//
////            Thread.sleep(10);
//
//            t1.interrupt(); // set flag isInterrupted to 'true'
//
////            t1.stop(); DO NOT USE
//
//            t1.join();
//            t2.join();

//            System.out.println(Thread.currentThread().getName());
//
//            MyThread myThread = new MyThread("My thread 1");
////        myThread.run(); // do not call; effortless
//            myThread.start();
//
//            Thread myThread2 = new MyThread("Thread 2");
//            myThread2.setDaemon(true);
//            myThread2.start();
//
//            Thread anonymousThread = new Thread() {
//                @Override
//                public void run() {
//                    for (;;) {
//                        System.out.println("Daemon thread");
//                    }
//                }
//            };
//            anonymousThread.setDaemon(true);
//            anonymousThread.start();
//
//
//            Runnable r1 = new MyRunnable("Runnable 1");
//            Thread runnableThread = new Thread(r1);
//            runnableThread.setPriority(Thread.MAX_PRIORITY);
//
//            runnableThread.start();
//
//            Runnable r2 = () -> {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("Thread of lambda" + ": " + i);
//                }
//
//                // retuns nothing
//            };
//            new Thread(r2).start();
//
//            myThread.join(); // blocking
//            myThread2.join(); // blocking



//            myThread2.join();
//            runnableThread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }

//        Thread t1 = new Thread();
//        t1.start();

//        Shape[] shapes = new Shape[] {
//            new Square(10),
//            new Square(25),
//            new Circle(12)
//        };
//
//        for (Shape s :
//                shapes) {
//            System.out.println(s.getClass().getSimpleName() + ": " +  s.area());
//        }

//        Function<String, Integer> f1 = s -> s.length();
//        Function<String, Integer> f2 = String::length;

        System.out.println("application ends");
    }
}
