package Assignment1;
import java.util.*;
import java.util.concurrent.locks.*;

public class FIFOSemaphore {
	private int permits;
	private int maxPermits;
	private Lock lock = new ReentrantLock();
	private LinkedList<Condition> queue = new LinkedList<>();
	private List<String> msg = new ArrayList<>();
	
	public int getPermits() {
		return permits;
	}

	public void setPermits(int permits) {
		this.permits = permits;
	}

	public List<String> getMsg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg = msg;
	}

	public FIFOSemaphore(int permits) {
		super();
		this.permits = permits;
		this.maxPermits = permits;
	}

	public void acquire() throws InterruptedException {
		this.lock.lock();
		try {
			this.msg.add("Acquire: ");
			if (this.permits == 0) {
				Condition c = this.lock.newCondition();
				this.msg.add("Acquire thread await!");
				this.msg.add("Permits: "+ this.permits);
				this.queue.add(c);
				
				c.await();
				
			} else {
				this.permits--;
				this.msg.add("Permits: "+ this.permits);
			}
		} finally {
			this.lock.unlock();
		}
	}

	public void release() {
		this.lock.lock();
		try {
			this.msg.add("Release: ");
			if (this.queue.size() > 0) {
				this.queue.poll().signal();;
				this.msg.add("Release thread!");
				this.msg.add("Permits: "+ this.permits);
			} else if(this.permits<this.maxPermits) {
				this.permits++;
				this.msg.add("Permits: "+ this.permits);
			}else {
				this.msg.add("There is nothing to release!");
			}
		} finally {
			this.lock.unlock();
		}
	}
}

