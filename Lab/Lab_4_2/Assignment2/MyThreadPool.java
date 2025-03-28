package Assignment2;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private int size;
    private List<Thread> threads = new ArrayList<>();
    private LinkedList<Runnable> tasks = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = this.lock.newCondition();
    private boolean running = true;

    public MyThreadPool() {
        this(3);
    }

    public MyThreadPool(int size) {
        this.size = size;

        for (int i = 0; i < this.size; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable task;
                    while (true) {
                        task = null;
                        MyThreadPool.this.lock.lock();
                        try {
                            while (MyThreadPool.this.tasks.isEmpty() == true) {
                                if (!running) {
                                    return;
                                }
                                try {
                                    MyThreadPool.this.cond.await();
                                } catch (InterruptedException e) {
                                    if (!running) {
                                        return;
                                    }
                                }
                            }
                            task = MyThreadPool.this.tasks.removeFirst();
                        } finally {
                            MyThreadPool.this.lock.unlock();
                        }
                        if (task != null) {
                            task.run();
                        }
                    }
                }
            });
            t.start();
            this.threads.add(t);
        }
    }

    public void add(Runnable task) {
        this.lock.lock();
        try {
            if (running) {
                this.tasks.add(task);
                this.cond.signal();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void shutdown() {
        this.lock.lock();
        try {
            running = false; 
            this.cond.signalAll(); 
        } finally {
            this.lock.unlock();
        }

        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
