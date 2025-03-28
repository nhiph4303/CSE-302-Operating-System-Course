package Q4;

public class Table {
    private String ingredient;
    private boolean isIngredientAvailable = false;
    private final Object lock = new Object();  // Lock for synchronization

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
        isIngredientAvailable = true;
    }

    public boolean isIngredientAvailable() {
        return isIngredientAvailable;
    }

    public void clearIngredient() {
        ingredient = null;
        isIngredientAvailable = false;
    }

    public Object getLock() {
        return lock;
    }
}
