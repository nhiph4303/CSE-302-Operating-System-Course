package Assignment2;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ThreadPool tp = new ThreadPool(3);

		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("20 + 70 = " + (20 + 70));

			}
		};
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello world!");
			}
		};
		Thread[] operators = new Thread[5];
		Thread[] helloWorld = new Thread[5];
		for(int i =0; i<5; i++) {
			operators[i] = new Thread(r1);
			helloWorld[i] = new Thread(r2);
			tp.add(operators[i]);
			tp.add(helloWorld[i]);
		}
		
		Thread.sleep(5000);
		tp.shutdown();
	}
}
