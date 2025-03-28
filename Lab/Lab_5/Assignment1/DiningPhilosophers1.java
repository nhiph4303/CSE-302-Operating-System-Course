import java.util.concurrent.locks.*;

public class DiningPhilosophers1 {
    private final static int N = 5;
    private Lock[] forks;

    public DiningPhilosophers1() {
        this.forks = new ReentrantLock[N];
        for (int i = 0; i < N; i++) {
            this.forks[i] = new ReentrantLock();
        }
    }

    public void getForks(int id) {
        if (id % 2 == 0) {
            this.forks[id].lock(); // Get the right fork
            this.forks[(id + 1) % N].lock(); // Get the left fork
        } else {
            this.forks[(id + 1) % N].lock(); // Get the left fork
            this.forks[id].lock(); // Get the right fork
        }
    }

    public void releaseForks(int id) {
        this.forks[id].unlock(); // Release right fork
        this.forks[(id + 1) % N].unlock(); // Release left fork
    }
}