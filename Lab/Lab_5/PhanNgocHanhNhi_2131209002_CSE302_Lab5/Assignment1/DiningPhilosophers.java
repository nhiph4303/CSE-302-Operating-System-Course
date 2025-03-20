import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);  
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            final int philosopherIndex = i;
            new Thread(() -> {
                try {
                    while (true) {
                        think(philosopherIndex); 
                        eat(philosopherIndex);  
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    // Philosopher's thinking period
    private static void think(int philosopherIndex) throws InterruptedException {
        System.out.println("Philosopher " + philosopherIndex + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000)); // Thinking for some time
    }

    // Philosopher's eating period
    private static void eat(int philosopherIndex) throws InterruptedException {
        int leftFork = philosopherIndex;
        int rightFork = (philosopherIndex + 1) % NUM_PHILOSOPHERS;

        // Ensure the philosopher picks forks in a non-deadlocking order
        if (philosopherIndex % 2 == 0) {
            forks[leftFork].acquire();
            System.out.println("Philosopher " + philosopherIndex + " picked up left fork " + leftFork);
            forks[rightFork].acquire();
            System.out.println("Philosopher " + philosopherIndex + " picked up right fork " + rightFork);
        } else {
            // Odd-indexed philosophers: pick up right fork first
            forks[rightFork].acquire();
            System.out.println("Philosopher " + philosopherIndex + " picked up right fork " + rightFork);
            forks[leftFork].acquire();
            System.out.println("Philosopher " + philosopherIndex + " picked up left fork " + leftFork);
        }

        // Philosopher eats
        System.out.println("Philosopher " + philosopherIndex + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));

        // Put down the forks
        forks[leftFork].release();
        System.out.println("Philosopher " + philosopherIndex + " put down left fork " + leftFork);
        forks[rightFork].release();
        System.out.println("Philosopher " + philosopherIndex + " put down right fork " + rightFork);
    }
}
