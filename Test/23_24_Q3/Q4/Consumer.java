package Q4;

public class Consumer implements Runnable {
    private final Table table;
    private final String neededIngredient;
    private final String consumerName;

    public Consumer(Table table, String neededIngredient, String consumerName) {
        this.table = table;
        this.neededIngredient = neededIngredient;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (table.getLock()) {
                    while (!table.isIngredientAvailable() || !table.getIngredient().equals(neededIngredient)) {
                        table.getLock().wait();  // Wait for the agent to notify
                    }
                    // Consume the ingredient
                    System.out.println(consumerName + " consumed " + table.getIngredient());
                    table.clearIngredient();
                    table.getLock().notifyAll();  // Notify agent to place next ingredient
                }
                Thread.sleep(1000);  // Simulate time taken to consume
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
