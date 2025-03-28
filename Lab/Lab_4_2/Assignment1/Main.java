import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        H2O h2o = new H2O(true);
        int N = 5;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < N * 2; i++) {
            HydrogenRunnable h = new HydrogenRunnable(h2o);
            Thread t = new Thread(h);
            threads.add(t);
            t.start();
        }

        for (int i = 0; i < N; i++) {
            OxygenRunnable o = new OxygenRunnable(h2o);
            Thread t = new Thread(o);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Done.");
    }
}

class HydrogenRunnable implements Runnable {
    private H2O h2o;

    public HydrogenRunnable(H2O h2o) {
        this.h2o = h2o;
    }

    @Override
    public void run() {
        try {
            h2o.hydrogen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OxygenRunnable implements Runnable {
    private H2O h2o;

    public OxygenRunnable(H2O h2o) {
        this.h2o = h2o;
    }

    @Override
    public void run() {
        try {
            h2o.oxygen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}