public class BarrierWorker extends Thread {
    private final Barrier barrier;

    public BarrierWorker(Barrier barrier, String name) {
        super(name);
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is doing some work...");
            Thread.sleep((int) (Math.random() * 1000)); 
            barrier.await(); 
            System.out.println(Thread.currentThread().getName() + " has passed the barrier!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
