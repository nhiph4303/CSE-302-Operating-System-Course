public class MultithreadingExample {
    public static void main(String[] args) {
        PrintHelloThread t = new PrintHelloThread();
        t.start();

        try {
            t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //runnable
        PrintHelloRunnable runnable = new PrintHelloRunnable();

        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
    }
}
