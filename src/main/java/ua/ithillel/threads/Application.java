package ua.ithillel.threads;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.io.server.ExecutorServiceServer;
import ua.ithillel.io.server.MultiThreadedServer;
import ua.ithillel.threads.counter.Counter;
import ua.ithillel.translator.Message;
import ua.ithillel.translator.MessageQueue;
import ua.ithillel.translator.Receiver;
import ua.ithillel.translator.Sender;
import ua.ithillel.translator.client.FunnyTranslationClient;
import ua.ithillel.translator.client.TranslatorClient;

import java.io.*;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Application {
    private static volatile int value; // from and to RAM
    private static volatile boolean ready; // from and to RAM

    public static void main(String[] args) {

//        final List<Future> tasks = Executors.newSingleThreadExecutor().invokeAll(task);
        List<Future> results = new ArrayList<>();

        while (results.size() > 0) {
            for (Future result:
                 results) {

                if (result.isDone()) {
                    results.remove(result);
                }
            }
        }


        try (final ExecutorServiceServer executorServiceServer
                     = new ExecutorServiceServer(8080, 2);) {

            executorServiceServer.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {

            Callable<Integer> randC1 = () -> (int)(100 * Math.random());
            Callable<Integer> randC2 = () -> (int)(100 * Math.random());
            Callable<Integer> randC3 = () -> (int)(100 * Math.random());
            Callable<Integer> randC4 = () -> (int)(100 * Math.random());
            Callable<Integer> randC5 = () -> (int)(100 * Math.random());

            List<Callable<Integer>> tasks = List.of(randC1, randC2, randC3, randC4, randC5);

            final ExecutorService executorService = Executors.newFixedThreadPool(2);

            final List<Future<Integer>> futures = executorService.invokeAll(tasks);

            for (Future<Integer> result:
                 futures) {
                System.out.println(result.get());
            }

//            executorService.shutdown();
            final List<Runnable> runnables = executorService.shutdownNow();

//            final Future<Integer> result1 = executorService.submit(randC1);
//            final Future<Integer> result2 = executorService.submit(randC2);
//            final Future<Integer> result3 = executorService.submit(randC3);
//            final Future<Integer> result4 = executorService.submit(randC4);
//            final Future<Integer> result5 = executorService.submit(randC5);



//            final CountDownLatch countDownLatch = new CountDownLatch(2);

//            Callable<String> c1 = () -> {
//                try (InputStream in = Application.class
//                        .getClassLoader()
//                        .getResourceAsStream("file1.txt");
//
//                     Reader rd = new InputStreamReader(in);
//                     BufferedReader br = new BufferedReader(rd);
//                ) {
//
//                    return br.lines().reduce((acc, cur) -> acc + cur + "\n").orElse("");
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    countDownLatch.countDown();
//                }
//            };
//
//            Callable<String> c2 = () -> {
//                try (InputStream in = Application.class
//                        .getClassLoader()
//                        .getResourceAsStream("file2.txt");
//
//                     Reader rd = new InputStreamReader(in);
//                     BufferedReader br = new BufferedReader(rd);
//                ) {
//
//                    return br.lines().reduce((acc, cur) -> acc + cur + "\n").orElse("");
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    countDownLatch.countDown();
//                }
//            };
//
//            final FutureTask<String> task1 = new FutureTask<>(c1);
//            final FutureTask<String> task2 = new FutureTask<>(c2);
//
//            new Thread(task1).start();
//            new Thread(task2).start();
//
//            String text1 = null;
//            while (true) {
//                if (task1.isDone()) {
//                    text1 = task1.get();
//                    break;
//                } else {
//                    System.out.println("Task 1 is not done yet");
//                    Thread.sleep(1);
//                }
//            }
//
//            String text2 = null;
//            while (true) {
//                if (task2.isDone()) {
//                    text2 = task2.get();
//                    break;
//                } else {
//                    System.out.println("Task 2 is not done yet");
//                    Thread.sleep(1);
//                }
//
//            }
//
//            System.out.println("Text: " + text1 + text2);



//            final CountDownLatch countDownLatch = new CountDownLatch(2);
//
//            StringBuffer buf1 = new StringBuffer();
//            StringBuffer buf2 = new StringBuffer();
//
//            Runnable r1 = () -> {
//                try (InputStream in = Application.class
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
//                } finally {
//                    countDownLatch.countDown();
//                }
//            };
//
//            Runnable r2 = () -> {
//                try (InputStream in = Application.class
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
//                } finally {
//                    countDownLatch.countDown();
//                }
//            };
//
//            Thread t1 = new Thread(r1);
//            Thread t2 = new Thread(r2);
//
//            t1.start();
//            t2.start();
//
//            final boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
//            System.out.println(buf1.append(buf2));


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


//
//        t1.join();
//        t2.join();

//        try {
//            final CountDownLatch countDownLatch = new CountDownLatch(2);
//
//            final Thread t1 = new Thread(() -> {
//                for (int i = 0; i < 200_000; i++) {
//                    System.out.println("t1: " + i);
//                }
//
//                countDownLatch.countDown();
//            });
//
//            final Thread t2 = new Thread(() -> {
//                for (int i = 0; i < 200_000; i++) {
//                    System.out.println("t1: " + i);
//                }
//
//                countDownLatch.countDown();
//            });
//
//            t1.start();
//            t2.start();
//
//            countDownLatch.await();
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//
//        }
//        final MutexCounter mutexCounter = new MutexCounter();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("t1: " + mutexCounter.incrementAndGet());
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("reader 1: " + mutexCounter.get());
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("reader 2: " + mutexCounter.get());
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("reader 3: " + mutexCounter.get());
//            }
//        }).start();

//        final MutexCounter mutexCounter = new MutexCounter();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("t1: " + mutexCounter.incrementAndGet());
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("t2: " + mutexCounter.incrementAndGet());
//            }
//        }).start();



//        new Thread(() -> {
//           while (!ready) {
//               Thread.yield(); // set to Runnable
//           }
//
//            System.out.println("T1 value: " + value);
//        }).start();
//
//
//        value = 34;
//        ready = true;
//
//

//        final AtomicCounter atomicCounter = new AtomicCounter();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("t1: " + atomicCounter.incrementAndGet());
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 200_000; i++) {
//                System.out.println("t2: " + atomicCounter.incrementAndGet());
//            }
//        }).start();


//        Runnable runnable = () -> {
//            try {
//                while (true) {
//                    System.out.println("Never ending task");
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        };
//
//
//        final Thread thread = new Thread(runnable);
//        thread.setDaemon(true);
//        thread.start();


//        final Thread t11 = new Thread(() -> {
//            System.out.println("T1");
//        });
//
//        t11.start();

        // Scheduler

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

        try(MultiThreadedServer server = new MultiThreadedServer(8080)) {
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
