import java.util.concurrent.Semaphore;

class SharedData {
    private int data = 0;
    private final Semaphore semaphore = new Semaphore(1);

    public void increase() {
        try {
            semaphore.acquire();
            data++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public void decrease() {
        try {
            semaphore.acquire();
            data--;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public int getData() {
        return data;
    }
}