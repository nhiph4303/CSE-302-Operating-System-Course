import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BridgeTest {
    public static void runTest() {
        OldBridge bridge = new OldBridge();
        ExecutorService executor = Executors.newFixedThreadPool(6);
        Random rand = new Random();

        for (int i = 1; i <= 6; i++) {
            int direction = rand.nextInt(2);
            executor.execute(new Car(bridge, direction, "Car-" + i));
        }

        executor.shutdown();
    }
}
