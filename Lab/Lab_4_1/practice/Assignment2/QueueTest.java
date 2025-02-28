import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class QueueTest {
    public static void runTest() {
        TSQueue queue = new TSQueue();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 3; i++) {
            executor.execute(new QueueInserter(queue, 10));
        }

        for (int i = 0; i < 2; i++) {
            executor.execute(new QueueRemover(queue, 15));
        }

        executor.shutdown();
    }
}
