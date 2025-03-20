public class BufferProducer extends Thread {
    private final BoundedBuffer buffer;
    private final int count;

    public BufferProducer(BoundedBuffer buffer, int count, String name) {
        super(name);
        this.buffer = buffer;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                buffer.add(i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}