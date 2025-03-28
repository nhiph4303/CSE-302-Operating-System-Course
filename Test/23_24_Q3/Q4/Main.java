package Q4;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();
        
        // Create consumers and agent
        Thread consumer1 = new Thread(new Consumer(table, "A", "Consumer C1"));
        Thread consumer2 = new Thread(new Consumer(table, "B", "Consumer C2"));
        Thread consumer3 = new Thread(new Consumer(table, "C", "Consumer C3"));
        Thread agent = new Thread(new Agent(table));
        
        // Start all threads
        agent.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        
        // Wait for all threads to finish (they run infinitely in this case)
        consumer1.join();
        consumer2.join();
        consumer3.join();
        agent.join();
    }
}
