public class Main {
    public static void main(String[] args) {
        DiningPhilosophers1 dp1 = new DiningPhilosophers1();

        Philosopher p0 = new Philosopher(0, dp1);
        Philosopher p1 = new Philosopher(1, dp1);
        Philosopher p2 = new Philosopher(2, dp1);
        Philosopher p3 = new Philosopher(3, dp1);
        Philosopher p4 = new Philosopher(4, dp1);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p0.shutdown();
        p1.shutdown();
        p2.shutdown();
        p3.shutdown();
        p4.shutdown();
    }
}

class Philosopher extends Thread {
    private int id;
    private DiningPhilosophers1 dp1;
    private boolean stop = false;

    public Philosopher(int id, DiningPhilosophers1 dp1) {
        this.id = id;
        this.dp1 = dp1;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                // thinking
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(100);

                // get forks
                System.out.println("Philosopher " + id + " is getting forks.");
                this.dp1.getForks(this.id);

                // eating
                System.out.println("Philosopher " + id + " is eating.");
                Thread.sleep(50);

                // releaseForks
                this.dp1.releaseForks(this.id);
                System.out.println("Philosopher " + id + " finish and return forks.");
            } catch (InterruptedException e) {
            }
            if (stop) {
                break;
            }
        }
    }

    public synchronized void shutdown() {
        stop = true;
        this.interrupt();
    }
}