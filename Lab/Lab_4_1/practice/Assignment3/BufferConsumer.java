public class BufferConsumer extends Thread {
    private final BoundedBuffer buffer;
    private final int count;

    public BufferConsumer(BoundedBuffer buffer, int count, String name) {
        super(name);
        this.buffer = buffer;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                buffer.remove();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}