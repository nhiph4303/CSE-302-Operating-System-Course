import java.util.concurrent.locks.ReentrantLock;

class SharedData {
    private int data = 0;
    private final ReentrantLock lock = new ReentrantLock(); // Khóa để bảo vệ biến data

    public void increase() {
        lock.lock();
        try {
            data++;
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            data--;
        } finally {
            lock.unlock();
        }
    }

    public int getData() {
        return data;
    }
}
