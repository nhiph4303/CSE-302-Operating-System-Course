import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierTest {
    public static void runTest() {
        int numberOfThreads = 5;
        Barrier barrier = new Barrier(numberOfThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 1; i <= numberOfThreads; i++) {
            executor.execute(new BarrierWorker(barrier, "Worker-" + i));
        }

        executor.shutdown();
    }
}
