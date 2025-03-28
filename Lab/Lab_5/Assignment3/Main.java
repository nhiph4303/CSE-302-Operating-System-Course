package Assignment3;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(3, 500);

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                boolean success = bank.transaction(threadId, (threadId + 1), 500);
                System.out.println(
                        "Transaction by account " + threadId + " to account " + (threadId + 1) + ": " + success);
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
