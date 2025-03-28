package Assignment2;

import java.util.Random;

public class Main {
    // private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool(5);

        for (int i = 0; i < 100; i++) {
            Task task = new Task(i);
            pool.add(task);
        }

        pool.shutdown();
    }

}

class Task implements Runnable {
    private int index;

    public Task(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        Random rd = new Random();
        try {
            Thread.sleep(rd.nextInt(100));
        } catch (InterruptedException e) {
        }
        System.out.println("Task" + this.index + "finished.");
    }
}
