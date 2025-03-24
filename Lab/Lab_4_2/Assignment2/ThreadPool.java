package Assignment2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
	private List<Thread> threads = new ArrayList<>();
	private LinkedList<Runnable> tasks = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition emptyCond = this.lock.newCondition();
	private boolean isStop = false;

	public ThreadPool() {
		this(3);
	}

	public ThreadPool(int size) {
		for (int i = 0; i < size; i++) {
			Thread t = new Thread(() -> {
				this.lock.lock();
				try {
					while (!isStop) {
						while (tasks.isEmpty()) {
							try {
								emptyCond.await();
								if (isStop)
									return;
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
								return;
							}
						}
						Runnable task = tasks.poll();
						if (Thread.interrupted())
							return;
						task.run();
					}
				} finally {
					this.lock.unlock();
				}
			});
			threads.add(t);
			t.start();
		}
	}

	public void add(Runnable task) {
		this.lock.lock();
		try {
			tasks.add(task);
			emptyCond.signal();
		} finally {
			this.lock.unlock();
		}
	}

	public void shutdown() {
		this.lock.lock();
		try {
			this.isStop = true;
			emptyCond.signalAll();
			for (Thread each : threads) {
				try {
					each.interrupt();
					each.join();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} finally {
			this.lock.unlock();
		}
	}
}
