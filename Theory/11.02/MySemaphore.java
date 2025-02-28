public class MySemaphore {
    private int permits;

    public MySemaphore(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Số lượng permits không thể nhỏ hơn 0");
        }
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if (permits == 0) {
            wait();
        } else {
            permits--;
        }
    }
    
    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized void acquire(int n) throws InterruptedException {
        if (permits < n) {
            wait();
        } else {
            permits -= n;
        }
    }

    public synchronized void release(int n) {
        permits += n;
        notifyAll(); 
    }
}
