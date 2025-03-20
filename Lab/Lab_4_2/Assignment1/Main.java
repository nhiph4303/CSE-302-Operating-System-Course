package Assignment1;
import java.util.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of permits: ");
		int permits = sc.nextInt();
		FIFOSemaphore fifoSemaphore = new FIFOSemaphore(permits);
		Runnable acquire = new Runnable() {
			@Override
			public void run() {
				try {
					fifoSemaphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		Runnable release = new Runnable() {

			@Override
			public void run() {
				fifoSemaphore.release();
			}
		};

		Thread[] release1 = new Thread[5];
		Thread[] accquire1 = new Thread[5];
		for (int i = 0; i < 5; i++) {
			accquire1[i] = new Thread(acquire);
			accquire1[i].start();
		}
		Thread.sleep(3000);
		for (int i = 0; i < 5; i++) {
			release1[i] = new Thread(release);
			release1[i].start();
		}
		for (int i = 0; i < 5; i++) {
			try {
				release1[i].join();
			} catch (InterruptedException e) {
			}
		}
		for (String each : fifoSemaphore.getMsg()) {
			System.out.println(each);
		}

	}
}
