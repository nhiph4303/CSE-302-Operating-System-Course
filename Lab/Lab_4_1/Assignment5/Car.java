public class Car extends Thread {
    private final OldBridge bridge;
    private final int direction;

    public Car(OldBridge bridge, int direction, String name) {
        super(name);
        this.bridge = bridge;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            bridge.arriveBridge(direction);
            Thread.sleep(500);
            bridge.exitBridge();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
