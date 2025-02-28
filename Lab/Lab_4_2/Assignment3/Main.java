import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FirstReadersWriters rw = new FirstReadersWriters();

        List<Thread> readThreads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            Thread t = new ReadThread(rw);
            readThreads.add(t);
            t.start();

        }

        List<Thread> writeThreads = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread t = new WriteThread(rw);
            writeThreads.add(t);
            t.start();

        }

        Thread.sleep(5000);

        for (Thread t : readThreads) {
            ((ReadThread) t).shutdown();
        }

        for (Thread t : writeThreads) {
            ((WriteThread) t).shutdown();
        }
    }
}

class ReadThread extends Thread {
    private FirstReadersWriters rw;
    private boolean running = true;

    public ReadThread(FirstReadersWriters rw) {
        this.rw = rw;
    }

    @Override
    public void run() {
        Random rd = new Random();
        try {
            while (this.running == true) {
                this.rw.readEnter();

                // reading
                System.out.println("I am reading...");

                Thread.sleep(rd.nextInt(100));

                System.out.println("Done.");

                this.rw.readExit();

                Thread.sleep(rd.nextInt(50));

            }
        } catch (InterruptedException e) {
        }

    }

    public void shutdown() {
        this.interrupt();
        this.running = false;
    }
}

class WriteThread extends Thread {
    private FirstReadersWriters rw;
    private boolean running = true;

    public WriteThread(FirstReadersWriters rw) {
        this.rw = rw;
    }

    @Override
    public void run() {
        Random rd = new Random();
        try {
            while (this.running == true) {
                this.rw.writeEnter();

                // reading
                System.out.println("I am writing...");

                Thread.sleep(rd.nextInt(100));

                System.out.println("Done.");

                this.rw.writeExit();

                Thread.sleep(rd.nextInt(50));

            }
        } catch (InterruptedException e) {
        }

    }

    public void shutdown() {
        this.interrupt();
        this.running = false;
    }
}