package ua.ithillel.threads.mult;

public class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupting T2");
                return;
            }
            System.out.println(name + ": " + i);
        }
    }
}
