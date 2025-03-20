public class QueueInserter extends Thread {
    private final TSQueue queue;
    private final int count;

    public QueueInserter(TSQueue queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            queue.addLast(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
