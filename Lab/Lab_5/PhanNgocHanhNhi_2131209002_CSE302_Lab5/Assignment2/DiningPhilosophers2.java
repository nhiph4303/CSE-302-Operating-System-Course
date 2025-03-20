package Assignment2;

import java.util.concurrent.atomic.AtomicInteger;

public class DiningPhilosophers2 {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final boolean[] chopsticks = new boolean[NUM_PHILOSOPHERS];
    private static final AtomicInteger usedChopsticks = new AtomicInteger(0);  // Đếm số đũa đang được sử dụng

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = false;
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

    private static void think(int philosopherIndex) throws InterruptedException {
        System.out.println("Philosopher " + philosopherIndex + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));  
    }

   
    private static void eat(int philosopherIndex) throws InterruptedException {
        int firstChopstick = philosopherIndex;
        int secondChopstick = (philosopherIndex + 1) % NUM_PHILOSOPHERS;

        if (canPickUpChopsticks(firstChopstick, secondChopstick)) {
            System.out.println("Philosopher " + philosopherIndex + " is eating.");
            Thread.sleep((long) (Math.random() * 1000)); 
            putDownChopsticks(firstChopstick, secondChopstick);
        } else {
            System.out.println("Philosopher " + philosopherIndex + " is waiting for chopsticks.");
        }
    }

    private static boolean canPickUpChopsticks(int firstChopstick, int secondChopstick) {
        if (usedChopsticks.get() >= NUM_PHILOSOPHERS - 1) {
            return false;
        }

        synchronized (chopsticks) {
            if (!chopsticks[firstChopstick] && !chopsticks[secondChopstick]) {
                chopsticks[firstChopstick] = true;
                chopsticks[secondChopstick] = true;
                usedChopsticks.incrementAndGet();
                return true;
            }
        }
        return false;
    }

    private static void putDownChopsticks(int firstChopstick, int secondChopstick) {
        synchronized (chopsticks) {
            chopsticks[firstChopstick] = false;
            chopsticks[secondChopstick] = false;
            usedChopsticks.decrementAndGet();
        }
        System.out.println("Philosopher put down chopsticks " + firstChopstick + " and " + secondChopstick);
    }
}
