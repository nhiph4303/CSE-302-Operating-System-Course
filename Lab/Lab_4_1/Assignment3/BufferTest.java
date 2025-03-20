import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BufferTest {
    public static void runTest() {
        BoundedBuffer buffer = new BoundedBuffer(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 2; i++) {
            executor.execute(new BufferProducer(buffer, 10, "Producer-" + i));
        }

        for (int i = 1; i <= 2; i++) {
            executor.execute(new BufferConsumer(buffer, 10, "Consumer-" + i));
        }

        executor.shutdown();
    }
}