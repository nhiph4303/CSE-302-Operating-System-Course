package Assignment4;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SecondReadersWriters database = new SecondReadersWriters();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of readers: ");
        int numReaders = sc.nextInt();
        System.out.println("Enter number of writers: ");
        int numWriters = sc.nextInt();

        Thread[] readers = new Thread[numReaders];
        Thread[] writers = new Thread[numWriters];

        for (int i = 0; i < numReaders; i++) {
            int readerId = i + 1;
            readers[i] = new Thread(() -> {
                try {
                    database.readEnter(readerId);
                    Thread.sleep(1000);
                    database.readExit(readerId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            readers[i].start();
        }

        for (int i = 0; i < numWriters; i++) {
            int writerId = i + 1;
            writers[i] = new Thread(() -> {
                try {
                    database.writeEnter(writerId);
                    Thread.sleep(2000);
                    database.writeExit(writerId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            writers[i].start();
        }

        for (Thread reader : readers) {
            try {
                reader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread writer : writers) {
            try {
                writer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All readers and writers have finished.");
        sc.close();
    }
}
