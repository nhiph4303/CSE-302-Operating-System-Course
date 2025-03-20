public class Main {
    public static final int n = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread t1 = new Thread(new IncreaseTask(data, n));
        Thread t2 = new Thread(new DecreaseTask(data, n));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("SharedData: " + data.getData());
    }
}