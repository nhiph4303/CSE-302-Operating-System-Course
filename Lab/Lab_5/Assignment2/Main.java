package Assignment2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DiningPhilosophers2 dp2 = new DiningPhilosophers2();

        List<Philosopher> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Philosopher p = new Philosopher(i, dp2);
            p.start();
            list.add(p);
        }

        Thread.sleep(1000);

        for (Philosopher p : list) {
            p.shutdown();
        }
    }
}

class Philosopher extends Thread {
    private DiningPhilosophers2 dp2;
    private int id;
    private boolean stop = false;

    public Philosopher(int id, DiningPhilosophers2 dp2) {
        this.id = id;
        this.dp2 = dp2;
    }

    @Override
    public void run() {
        Random rd = new Random();
        while (!stop) {
            try {
                // thinking
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(rd.nextInt(100));

                // get forks
                System.out.println("Philosopher " + id + " is getting forks.");
                this.dp2.getForks();

                // eating
                System.out.println("Philosopher " + id + " is eating.");
                Thread.sleep(rd.nextInt(50));

                // release forks
                this.dp2.releaseForks();
                System.out.println("Philosopher " + id + " finished eating and returned forks.");
            } catch (InterruptedException e) {

            }
        }
    }

    public synchronized void shutdown() {
        stop = true;
        this.interrupt();
    }
}
