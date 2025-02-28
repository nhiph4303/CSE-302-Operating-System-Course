public class QueueRemover extends Thread {
    private final TSQueue queue;
    private final int count;

    public QueueRemover(TSQueue queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                queue.removeFirst();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
