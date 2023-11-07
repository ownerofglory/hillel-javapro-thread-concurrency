package ua.ithillel.threads.mult;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (interrupted()) {
                return;
            }
            System.out.println(getName() + ": " + i);
        }
    }
}
