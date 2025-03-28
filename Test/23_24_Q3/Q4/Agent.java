package Q4;

import java.util.Random;

public class Agent implements Runnable {
    private final Table table;
    private final Random random = new Random();

    public Agent(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String[] ingredients = {"A", "B", "C"};
                String ingredient = ingredients[random.nextInt(3)];  // Randomly select an ingredient
                synchronized (table.getLock()) {
                    table.setIngredient(ingredient);
                    System.out.println("Agent placed ingredient " + ingredient + " on the table.");
                    table.getLock().notifyAll();  // Notify all consumers
                }
                Thread.sleep(2000);  // Simulate time taken to generate a new ingredient
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
